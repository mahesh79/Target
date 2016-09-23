package com.myretail.daos;


public class ExternalProductResponseDAO {

    private int statusCode;
    private String productDescription;

    public ExternalProductResponseDAO() {
    }

    public ExternalProductResponseDAO(int statusCode, String productDescription) {
        this.statusCode = statusCode;
        this.productDescription = productDescription;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
