package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import java.util.List;

public class ProductCategory {

    private String libelle;

    private List<String> ancestors;

    private String parent;

    public ProductCategory(String libelle, List<String> ancestors, String parent) {
        this.libelle = libelle;
        this.ancestors = ancestors;
        this.parent = parent;
    }

    public ProductCategory(){

    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<String> getAncestors() {
        return ancestors;
    }

    public void setAncestors(List<String> ancestors) {
        this.ancestors = ancestors;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
