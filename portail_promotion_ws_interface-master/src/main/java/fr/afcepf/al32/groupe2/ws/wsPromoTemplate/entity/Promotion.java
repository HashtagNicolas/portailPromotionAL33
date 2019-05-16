package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "promotion_id")
    private Long promotionId;

    @Column(name="timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private String name;

    private String description;

    @Column(name="date_of_creation")
    @Temporal(TemporalType.TIMESTAMP)
    @Indexed
    private Date dateCreation;

    @Column(name="date_of_remove")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRemove;

    @Column(name="limit_time_promotion")
    private Duration limitTimePromotion;

    @Column(name="limit_time_take_promotion")
    private Duration limitTimeTakePromotion;

    @Column(name="reserved_product_percentage")
    private Double reservedProductPercentage;

    @Column(name="is_cumulative")
    private Boolean isCumulative;

    @OneToMany(mappedBy = "promotionFK", fetch=FetchType.EAGER)
    private List<Shop> shops = new ArrayList<>();

    @Embedded
    @Column(name="promotion_type")
    private PromotionType promotionType;

    @Embedded
    private Product product;

    @OneToMany(mappedBy = "promotionFK", fetch=FetchType.EAGER)
    private List<Reservation> reservations = new ArrayList<>();

    public Promotion(Long promotionId, String name, String description, Date dateCreation, Duration limitTimePromotion, Duration limitTimeTakePromotion, Double reservedProductPercentage, Boolean isCumulative, PromotionType promotionType, Product product) {
        this.promotionId = promotionId;
        this.name = name;
        this.timestamp = new Date();
        this.description = description;
        this.dateCreation = dateCreation;
        this.limitTimePromotion = limitTimePromotion;
        this.limitTimeTakePromotion = limitTimeTakePromotion;
        this.reservedProductPercentage = reservedProductPercentage;
        this.isCumulative = isCumulative;
        this.promotionType = promotionType;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateRemove() {
        return dateRemove;
    }

    public void setDateRemove(Date dateRemove) {
        this.dateRemove = dateRemove;
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

    public Boolean getCumulative() {
        return isCumulative;
    }

    public void setCumulative(Boolean cumulative) {
        isCumulative = cumulative;
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

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
