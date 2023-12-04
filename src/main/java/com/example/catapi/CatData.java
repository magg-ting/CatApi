/**
 * This class contains the details of a particular cat image. JSON data fetched from the API will be parsed to create a CatData object where necessary.
 * In the Cat API, each breed may contain more than one cat images, so searching a particular breed may give different result images every time.
 */
package com.example.catapi;

public class CatData {
    // The id and url of each cat image are unique
    private String imgId;
    private String imgUrl;
    private Breed breed;

    // Constructor
    public CatData(String imgId, String imgUrl, Breed breed){
        this.imgId = imgId;
        this.imgUrl = imgUrl;
        this.breed = breed;
    }

    // Getters and Setters
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
