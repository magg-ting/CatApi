package com.example.catapi;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CatApiUtil {
    private final static String api_key = "live_rjnqQtVr2wc70tHNi1OwEWvTOQt5AViFL9Xi36XucrByRSRZCyMmJhZdDtnjOKY7";
    public static String fetchJsonData(String base_url, String params) throws IOException{
        URL full_url = new URL(base_url + "?" + "api_key=" + api_key + params);
        System.out.println(full_url);
        HttpURLConnection conn = (HttpURLConnection) full_url.openConnection();

        conn.setRequestMethod("GET");
        conn.setReadTimeout(5000);

        int responseCode = conn.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();
            return response.toString();
        }
        else{
            throw new IOException("Failed to fetch data. HTTP error code: " + responseCode);
        }
    }

    public static CatData parseImg(String json){
        JSONArray jsonArray = new JSONArray(json);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String imgId = jsonObject.getString("id");
        String imgUrl = jsonObject.getString("url");
        Breed breed = new Breed();
        return new CatData(imgId, imgUrl, breed);
    }

    public static List<Breed> parseAllBreeds(){
        List<Breed> allBreeds = new ArrayList<Breed>();
        String base_url = "https://api.thecatapi.com/v1/breeds";
        String params = "";
        try{
            System.out.println("Getting all available breed names...");
            String json = fetchJsonData(base_url, params);
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Breed breed = parseBreedFromJson(jsonObject);
                allBreeds.add(breed);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return allBreeds;
    }

    public static Breed parseBreedFromImg(String imgId){
        String base_url = "https://api.thecatapi.com/v1/images/";
        try{
            System.out.println("Getting breed data from image id...");
            String json = fetchJsonData(base_url + imgId, "");
            JSONObject jsonObject = new JSONObject(json);
            JSONObject breedObj = jsonObject.getJSONArray("breeds").getJSONObject(0);
            return parseBreedFromJson(breedObj);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new Breed();
    }

    public static Breed parseBreedFromJson(JSONObject jsonObject){
        Breed breed = new Breed();
        try{
            String breedId = jsonObject.getString("id");
            String name = jsonObject.getString("name");

            // Since not all attributes are present in every breed, we'll extract the following by optString() method to return an empty string by default
            String temperament = jsonObject.optString("temperament");
            String origin = jsonObject.optString("origin");
            String weight = jsonObject.getJSONObject("weight").optString("metric");
            String lifespan = jsonObject.optString("life_span");
            String description = jsonObject.optString("description");
            String wikiUrl = jsonObject.optString("wikipedia_url");
            breed.setBreedId(breedId);
            breed.setName(name);
            breed.setTemperament(temperament);
            breed.setOrigin(origin);
            breed.setWeight(weight + " kg");
            breed.setLifespan(lifespan + " years");
            breed.setDescription(description);
            breed.setWikiUrl(wikiUrl);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return breed;
    }
}
