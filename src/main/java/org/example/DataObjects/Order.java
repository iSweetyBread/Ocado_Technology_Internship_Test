package org.example.DataObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    @JsonProperty("id")
    private String Id;
    @JsonProperty("value")
    private double Value;
    @JsonProperty("promotions")
    private ArrayList<String> Promotions = new ArrayList<>();

    public Order(){}
    @JsonCreator
    public Order(@JsonProperty("id") String id, @JsonProperty("value") double value, @JsonProperty("promotions") List<String> promotions){
        this.Id = id;
        this.Value = value;
        if(promotions != null){
            this.Promotions.addAll(promotions);
        }
        this.Promotions.add("PUNKTY");
    }
    public Order(String id, float value, String promotions){
        this.Id = id;
        this.Value = value;
        this.Promotions.add(promotions);
        this.Promotions.add("PUNKTY");
    }
    public Order(String id, float value){
        this.Id = id;
        this.Value = value;
        this.Promotions.add("PUNKTY");
    }

    public void setId(String id){ this.Id = id; }
    public void setValue(double value){ this.Value = value; }
    public void setPromotions(String[] promotions){
        this.Promotions = new ArrayList<>();
        this.Promotions.addAll(Arrays.asList(promotions));
    }

    public String getId(){ return this.Id; }
    public double getValue(){ return this.Value; }
    public ArrayList<String> getPromotions(){ return this.Promotions; }
}
