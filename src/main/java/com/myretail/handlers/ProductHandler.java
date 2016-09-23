package com.myretail.handlers;


import com.myretail.services.ProductDetailsDTO;
import com.myretail.services.ProductService;

import org.apache.http.HttpStatus;
import io.netty.handler.codec.http.HttpResponseStatus;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class ProductHandler implements Handler {
    private final static Logger log = LoggerFactory.getLogger(ProductHandler.class);
    private final ProductService productService;


    @Inject
    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

        try {
            String id = ctx.getPathTokens().get("tcin");

            if (!id.matches("[0-9]+") || id.length() > 8){
                ctx.getResponse().status(HttpStatus.SC_BAD_REQUEST).send("not a valid tcin");
            }
            //Get the response and validate for 200
            ProductDetailsDTO productDetails = productService.byTcin(id);
            if (productDetails.getReturnCode() == HttpResponseStatus.OK.code()) {
                ctx.getResponse().status(HttpStatus.SC_OK);
                ctx.render(json(productDetails.getProduct()));
            } else if (productDetails.getReturnCode() == HttpResponseStatus.NOT_FOUND.code()) {
                ctx.getResponse().status(HttpStatus.SC_NOT_FOUND).send("pricing or name does not exist for the given tcin");
            } else {
                ctx.getResponse().status(HttpStatus.SC_BAD_REQUEST).send("bad request");
            }
        } catch (Exception ex) {
            log.error("PricingDAOImpl Class" + ex.getMessage());
            ctx.getResponse().send("Unexpected error in Product Service");
        }
    }
}
