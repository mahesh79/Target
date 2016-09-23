package com.myretail.modules;


import com.google.inject.AbstractModule;
import com.myretail.daos.PricingDAO;
import com.myretail.daos.PricingDAOImpl;
import com.myretail.handlers.ProductHandler;
import com.myretail.services.ProductService;
import com.myretail.services.impl.ProductServiceImpl;
import com.myretail.daos.ProductDAO;
import com.myretail.daos.ProductDAOImpl;

public class ProductModule extends AbstractModule {

    protected void configure() {

        bind(ProductDAO.class).to(ProductDAOImpl.class);
        bind(PricingDAO.class).to(PricingDAOImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(ProductHandler.class);

    }
}
