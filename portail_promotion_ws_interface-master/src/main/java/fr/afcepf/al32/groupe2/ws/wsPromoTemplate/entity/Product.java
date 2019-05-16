package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import javax.persistence.*;
import java.util.Date;

public class Product {

    @Column(name = "base_product_id")
    private Long baseProductId;

    @Column(name = "reference_product_id")
    private Long referenceProductId;

    @Column(name="init_price")
    private Double initPrice;

    @Column(name="description")
    private String description;

    @Column(name="add_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;

    @Column(name="remove_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date removeDate;

    private String libelle;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory category;

    public Product(Long baseProductId, Long referenceProductId, Double initPrice, String description, Date addDate, String libelle, ProductCategory category) {
        this.baseProductId = baseProductId;
        this.referenceProductId = referenceProductId;
        this.initPrice = initPrice;
        this.description = description;
        this.addDate = addDate;
        this.libelle = libelle;
        this.category = category;
    }

    public Product(){

    }

    public Long getBaseProductId() {
        return baseProductId;
    }

    public void setBaseProductId(Long baseProductId) {
        this.baseProductId = baseProductId;
    }

    public Long getReferenceProductId() {
        return referenceProductId;
    }

    public void setReferenceProductId(Long referenceProductId) {
        this.referenceProductId = referenceProductId;
    }

    public Double getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(Double initPrice) {
        this.initPrice = initPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
