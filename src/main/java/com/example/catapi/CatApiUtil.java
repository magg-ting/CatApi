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
//      String base_url = "https://api.thecatapi.com/v1/images/search";
//      String api_key = "live_rjnqQtVr2wc70tHNi1OwEWvTOQt5AViFL9Xi36XucrByRSRZCyMmJhZdDtnjOKY7";
        URL full_url = new URL(base_url + "?" + params + "&api_key=" + api_key);
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
        //String breedId =jsonObject.getJSONArray("breeds").getJSONObject(0).getString("id");
        Breed breed = new Breed();
        //breed.setBreedId(breedId);
        return new CatData(imgId, imgUrl, breed);
    }

    public static List<Breed> parseAllBreeds(){
        List<Breed> allBreeds = new ArrayList<Breed>();
        String base_url = "https://api.thecatapi.com/v1/breeds";
        String params = "";
        try{
            String json = fetchJsonData(base_url, params);
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String breedId = jsonObject.getString("id");
                String name = jsonObject.getString("name");

                // Since not all attributes are present in every breed, we'll extract the following by optString() method to return an empty string by default
                String temperament = jsonObject.optString("temperament");
                String origin = jsonObject.optString("origin");
                String weight = jsonObject.getJSONObject("weight").optString("metric");
                String lifespan = jsonObject.optString("life_span");
                String wikiUrl = jsonObject.optString("wikipedia_url");
                Breed breed = new Breed(breedId, name, temperament, origin, weight, lifespan, wikiUrl);
                allBreeds.add(breed);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return allBreeds;
    }
}
