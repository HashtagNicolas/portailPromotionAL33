package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PromotionType {

    @Column(name = "promotion_type_id")
    private Long promotionTypeId;

    protected String _class;

    public PromotionType(Long promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }

    public Long getPromotionTypeId() {
        return promotionTypeId;
    }

    public void setPromotionTypeId(Long promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getName(){
        return null;
    }
}
