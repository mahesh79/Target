package com.myretail.services;

/**
 * ProductService defines myretail for finding product details within  My Retail application.
 */
public interface ProductService {

    /**
     * @return a collection of all product within My Retail.
     */

    ProductDetailsDTO byTcin(String id);

}
