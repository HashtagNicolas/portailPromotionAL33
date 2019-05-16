package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.Product;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.PromotionType;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.util.PromotionTypeDeserializer;

import java.time.Duration;
import java.util.Date;

public class PromotionTemplateResultDto {
    private Long promotionId;

    private Date timestamp;

    private String name;

    private String description;

    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration limitTimePromotion;

    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration limitTimeTakePromotion;

    private Double reservedProductPercentage;

    @JsonDeserialize(using = PromotionTypeDeserializer.class)
    private PromotionType promotionType;

    private Product product;

    private Double dist;

    private int reservationsNumber;
    

    
//    public PromotionTemplateResultDto() {
//		super();
//	}
//
//	public PromotionTemplateResultDto(Long promotionId, Date timestamp, String name, String description,
//			Duration limitTimePromotion, Duration limitTimeTakePromotion, Double reservedProductPercentage,
//			PromotionType promotionType, Product product, Double dist, int reservationsNumber) {
//		super();
//		this.promotionId = promotionId;
//		this.timestamp = timestamp;
//		this.name = name;
//		this.description = description;
//		this.limitTimePromotion = limitTimePromotion;
//		this.limitTimeTakePromotion = limitTimeTakePromotion;
//		this.reservedProductPercentage = reservedProductPercentage;
//		this.promotionType = promotionType;
//		this.product = product;
//		this.dist = dist;
//		this.reservationsNumber = reservationsNumber;
//	}

	public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getLimitTimePromotion() {
        return limitTimePromotion;
    }

    public void setLimitTimePromotion(Duration limitTimePromotion) {
        this.limitTimePromotion = limitTimePromotion;
    }

    public Duration getLimitTimeTakePromotion() {
        return limitTimeTakePromotion;
    }

    public void setLimitTimeTakePromotion(Duration limitTimeTakePromotion) {
        this.limitTimeTakePromotion = limitTimeTakePromotion;
    }

    public Double getReservedProductPercentage() {
        return reservedProductPercentage;
    }

    public void setReservedProductPercentage(Double reservedProductPercentage) {
        this.reservedProductPercentage = reservedProductPercentage;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getDist() {
        return dist;
    }

    public void setDist(Double dist) {
        this.dist = dist;
    }

    public int getReservationsNumber() {
        return reservationsNumber;
    }

    public void setReservationsNumber(int reservationsNumber) {
        this.reservationsNumber = reservationsNumber;
    }
}
