package fr.afcepf.al32.groupe2.ws.dto;

import java.util.List;

public class OrchestratorResearchDtoResponse {
    private Long categoryId;

    private List<String> keyWords;

    private String source;

    private Integer perimetre;

    private List<PromotionDto> promotions;

    private Boolean addressValid;
    
    private List<Double> coordonnees;

    public OrchestratorResearchDtoResponse(Long categoryId, List<String> keyWords, String source, Integer perimetre, List<PromotionDto> promotions, Boolean addressValid, List<Double> coordonnees) {
        this.categoryId = categoryId;
        this.keyWords = keyWords;
        this.source = source;
        this.perimetre = perimetre;
        this.promotions = promotions;
        this.addressValid = addressValid;
        this.coordonnees= coordonnees;
    }

    public OrchestratorResearchDtoResponse(){}

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getPerimetre() {
        return perimetre;
    }

    public void setPerimetre(Integer perimetre) {
        this.perimetre = perimetre;
    }

    public List<PromotionDto> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<PromotionDto> promotions) {
        this.promotions = promotions;
    }

    public Boolean getAddressValid() {
        return addressValid;
    }

    public void setAddressValid(Boolean addressValid) {
        this.addressValid = addressValid;
    }

	public List<Double> getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(List<Double> coordonnees) {
		this.coordonnees = coordonnees;
	}
    
    
}
