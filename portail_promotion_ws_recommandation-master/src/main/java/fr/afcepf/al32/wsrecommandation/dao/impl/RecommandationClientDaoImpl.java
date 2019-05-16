package fr.afcepf.al32.wsrecommandation.dao.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;
import fr.afcepf.al32.wsrecommandation.dao.IRecommandationClientDao;

@Component
public class RecommandationClientDaoImpl implements IRecommandationClientDao {

	@Autowired
	private MongoOperations mongoOps;

	@Override
	public List<PromotionTemplateResultDto> findDixPromotionsPrefereesByCategorie(Point sourcePoint, String category) {

		MongoCollection<Document> promotionCollection = mongoOps.getCollection("promotions");

		/*
		 * Etape d'aggrégation pour la recherche géographique
		 */
		String geoNearStep = String.format(
				"{$geoNear: {near: { type: 'Point', coordinates: [ %s , %s ] }, distanceField: 'dist', key: 'shops.location', num: 10 }}",
				sourcePoint.getX(), sourcePoint.getY());
		Document geoNear = Document.parse(geoNearStep);

		LocalDateTime dateTime = LocalDateTime.now().minusMonths(3L);
		String date = dateTime.format(DateTimeFormatter.ISO_DATE);

		/*
		 * Etape d'aggrégation pour une sélection des promotions sur la date de création
		 * et la catégorie des produits.
		 */
		String matchStep = String.format(
				"{$match: {$and: [{dateCreation: {$gte: ISODate('%s')}}, {$or: [{'product.category.libelle': {$eq: '%s'}}, {'product.category.ancestors': {$eq: '%s'}}]}]}}",
				date, category, category);

		Document match = Document.parse(matchStep);

		/*
		 * Etape d'aggrégation pour exclure certains champs du retour.
		 */
		Document exclusionProjection = Document
				.parse("{$project: { dateCreation: 0, isCumulative: 0, shops: 0, 'product.addDate': 0 }}");

		/*
		 * Etape d'aggrégation pour indiquer les champs du retour et en créer un
		 * nouveau, reservationNumber.
		 */
		Document inclusionProjection = Document.parse(
				"{$project: { promotionId: 1, name : 1, description : 1, limitTimePromotion : 1, limitTimeTakePromotion : 1, reservedProductPercentage : 1, dist: 1, promotionType : 1, product : 1, _class : 1, timestamp: 1, reservationsNumber: {$size: '$reservations'} }}");

		/*
		 * Etape d'aggrégation pour grouper les résultats, permettant de garder pour une
		 * même promotion, au sens BDD relationnel, celui avec le timestamp le plus
		 * récent.
		 */
		Document group = Document.parse(
				"{$group: { _id : {promotionId: '$promotionId', name : '$name', description : '$description', limitTimePromotion : '$limitTimePromotion', limitTimeTakePromotion : '$limitTimeTakePromotion', reservedProductPercentage : '$reservedProductPercentage', dist: '$dist', promotionType : '$promotionType', product : '$product', _class : '$_class', reservationsNumber: '$reservationsNumber'}, timestamp : {$max: '$timestamp'}} }");

		/*
		 * Etape replaçant la racine de l'objet en retour ce qui était au préalable dans
		 * le champ _id (c'est ce qui nous intéresse)
		 */
		Document replaceRoot = Document.parse("{$replaceRoot: {newRoot: '$_id'}}");

		/*
		 * Etape d'aggrégation de tri
		 */
		Document sort = Document.parse("{$sort: {reservedProductPercentage: -1, reservationsNumber: -1, dist: 1}}");

		/*
		 * Etape limitant le nombre de résultat retourné aux cinq premiers.
		 */
		Document limit = Document.parse("{$limit: 10}");

		List<Document> aggregationList = new ArrayList<>();

		aggregationList.add(geoNear);
		aggregationList.add(match);
		aggregationList.add(exclusionProjection);
		aggregationList.add(inclusionProjection);
		aggregationList.add(group);
		aggregationList.add(replaceRoot);
		aggregationList.add(sort);
		aggregationList.add(limit);

		AggregateIterable<Document> iterable = promotionCollection.aggregate(aggregationList);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		List<PromotionTemplateResultDto> result = new ArrayList<>();

		JsonWriterSettings settings = JsonWriterSettings.builder()
				.int64Converter((value, writer) -> writer.writeNumber(value.toString())).build();

		iterable.forEach((Block<Document>) document -> {
			try {
				result.add(mapper.readValue(document.toJson(settings), PromotionTemplateResultDto.class));

			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		return result;
	}

}
