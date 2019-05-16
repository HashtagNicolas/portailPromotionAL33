package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.Discount;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.Pack;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.PercentType;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.PromotionType;

import java.io.IOException;

public class PromotionTypeDeserializer extends StdDeserializer<PromotionType> {


    protected PromotionTypeDeserializer(Class<?> vc) {
        super(vc);
    }

    public PromotionTypeDeserializer(){
        this(null);
    }

    @Override
    public PromotionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String className = node.get("_class").textValue();
        switch (className){
            case "fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.PercentType":
                return new PercentType(node.get("promotionTypeId").longValue(), node.get("percentValue").doubleValue(), node.get("minPurchaseAmount").doubleValue());

            case "fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.Pack":
                return new Pack(node.get("promotionTypeId").longValue(), node.get("numberPurchased").intValue(), node.get("numberOffered").intValue());

            case "fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.Discount":
                return new Discount(node.get("promotionTypeId").longValue(), node.get("discountValue").doubleValue(), node.get("minPurchaseAmount").doubleValue());

            default:
                return null;
        }
    }
}
