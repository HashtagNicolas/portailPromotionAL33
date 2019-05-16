package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto;

import java.util.List;

public class TopPromotionTemplateResultDto {

    private Double sourceLong;

    private Double sourceLat;

    private List<String> categories;

    private List<PromotionTemplateResultDto> templates;

    public TopPromotionTemplateResultDto() {
    }

    public TopPromotionTemplateResultDto(Double sourceLong, Double sourceLat, List<String> categories, List<PromotionTemplateResultDto> templates) {
        this.sourceLong = sourceLong;
        this.sourceLat = sourceLat;
        this.categories = categories;
        this.templates = templates;
    }

    public Double getSourceLong() {
        return sourceLong;
    }

    public void setSourceLong(Double sourceLong) {
        this.sourceLong = sourceLong;
    }

    public Double getSourceLat() {
        return sourceLat;
    }

    public void setSourceLat(Double sourceLat) {
        this.sourceLat = sourceLat;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<PromotionTemplateResultDto> getTemplates() {
        return templates;
    }

    public void setTemplates(List<PromotionTemplateResultDto> templates) {
        this.templates = templates;
    }
}
