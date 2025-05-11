package org.example.DataObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethods {
    @JsonProperty("id")
    private String Id;
    @JsonProperty("discount")
    private int Discount;
    @JsonProperty("limit")
    private double Limit;

    public PaymentMethods(){}
    public PaymentMethods(String id, int discount, double limit){
        this.Id = id;
        this.Discount = discount;
        this.Limit = limit;
    }

    public void setId(String id){ this.Id = id; }
    public void setDiscount(int discount){ this.Discount = discount; }
    public void setLimit(double limit){ this.Limit = limit; }

    public String getId(){ return this.Id; }
    public int getDiscount(){ return this.Discount; }
    public double getLimit(){ return this.Limit; }
}
