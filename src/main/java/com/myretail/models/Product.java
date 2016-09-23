package com.myretail.models;


import com.fasterxml.jackson.annotation.*;
import javax.annotation.Generated;

/**
 * Product represents tcin, description and the pricing for a given TCIN
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"id","name","pricing"})
public class Product {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pricing")
    private Pricing pricing;

    public Product() {}

    public Product(
            String id,
            String name,
            Pricing pricing)
    {


        this.id = id;
        this.name = name;
        this.pricing = pricing;
    }

    public String getid() { return this.id; }
    public void setid(String id) {
        this.id = id;
    }

    public String getName() { return this.name; }
    public void setName(String name) {
        this.name = name;
    }

    public Pricing getPricing() { return this.pricing; }
    public void setPricing(Pricing pricing) {this.pricing = pricing; }


}
