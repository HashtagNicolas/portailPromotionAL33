package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;

public class Shop {

    @Column(name = "shop_id")
    private Long shopId;

    private GeoJsonPoint location;

    @Column(name = "shopkeeper_id")
    private Long shopkeeperId;

    public Shop(Long shopId, Point location, Long shopkeeperId) {
        this.shopId = shopId;
        this.location = new GeoJsonPoint(location.getX(), location.getY());
        this.shopkeeperId = shopkeeperId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public Long getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Long shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }
}
