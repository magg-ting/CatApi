package com.example.catapi;

public class CatData {
    private String imgId;
    private String imgUrl;
    private Breed breed;

    public CatData(String imgId, String imgUrl, Breed breed){
        this.imgId = imgId;
        this.imgUrl = imgUrl;
        this.breed = breed;
    }

    public void setImgId(String imgId){
        this.imgId = imgId;
    }
    public String getImgId(){
        return imgId;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public String getImgUrl(){
        return imgUrl;
    }
    public void setBreed(Breed breed){
        this.breed = breed;
    }
    public Breed getBreed(){
        return breed;
    }
}
