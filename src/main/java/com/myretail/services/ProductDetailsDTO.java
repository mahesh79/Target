package com.myretail.services;

import com.myretail.models.Product;


public class ProductDetailsDTO {


    private int returnCode;
    private Product product;

    public ProductDetailsDTO(){}
    public ProductDetailsDTO(int returnCode, Product product){
        this.returnCode = returnCode;
        this.product = product;
    };

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getReturnCode() { return returnCode; }

    public void setReturnCode(int returnCode) { this.returnCode = returnCode; }


}
