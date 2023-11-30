package com.example.catapi;

public class Breed {
    public String breedId;
    public String name;
    public String temperament;
    public String origin;
    public String weight;
    public String lifespan;
    public String wikiUrl;
    public Breed(){};
    public Breed(String breedId, String name, String temperament, String origin, String weight, String lifespan, String wikiUrl){
        this.breedId = breedId;
        this.name = name;
        this.temperament = temperament;
        this.origin = origin;
        this.weight = weight;
        this.lifespan = lifespan;
        this.wikiUrl = wikiUrl;
    }

    public void setBreedId(String breedId){
        this.breedId = breedId;
    }
    public String getBreedId(){
        return breedId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setTemperament(String temperament){
        this.temperament = temperament;
    }
    public String getTemperament(){
        return temperament;
    }
    public void setOrigin(String origin){
        this.origin = origin;
    }
    public String getOrigin(){
        return origin;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public String getWeight(){
        return weight;
    }
    public void setLifespan(String lifespan){
        this.lifespan = lifespan;
    }
    public String getLifespan(){
        return lifespan;
    }
    public void setWikiUrl(String wikiUrl){
        this.wikiUrl = wikiUrl;
    }
    public String getWikiUrl(){
        return wikiUrl;
    }

    // Override toString() method to return the breed's name in the ComboBox
    @Override
    public String toString() {
        return name;
    }
}
