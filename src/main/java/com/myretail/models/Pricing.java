package com.myretail.models;

import com.fasterxml.jackson.annotation.*;
import com.google.inject.Inject;

import javax.annotation.Generated;

/**
 * Pricing represents the value and currency code for a given TCIN.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"currency_code","value"})
public class Pricing {

    @JsonProperty("currency_code")
    private String currencyCode;
    @JsonProperty("value")
    private Double value;

    @Inject
    public Pricing() {}
    /**
     * Constructs a new instance of Pricing.
     */
    public Pricing(
        String currencyCode,
        Double value)
    {
        this.currencyCode = currencyCode;
        this.value = value;
    }


    public String getCurrencyCode() { return this.currencyCode; }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getValue() { return this.value; }
    public void setValue(Double value) {this.value = value; }

}
