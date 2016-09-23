package com.myretail.services.impl;

import com.myretail.daos.ExternalProductResponseDAO;
import com.myretail.daos.PricingDAO;
import com.myretail.daos.ProductDAO;
import com.myretail.models.Pricing;
import com.myretail.models.Product;
import com.myretail.services.ProductDetailsDTO;
import com.myretail.services.ProductService;
import io.netty.handler.codec.http.HttpResponseStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * ProductServiceImpl is the default implementation of the ProductService interface.
 */
public class ProductServiceImpl implements ProductService {

    private final static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private PricingDAO pricingDAO;
    private ProductDAO productDAO;

    /**
     * Creates a new instance of ProductServiceImpl.
     *
     * @param pricingDAO an instance of a PricingDAO and productDAO as instance of ProductDAO
     */
    @Inject
    public ProductServiceImpl(ProductDAO productDAO, PricingDAO pricingDAO) {

        checkNotNull(pricingDAO);
        checkNotNull(productDAO);

        this.pricingDAO = pricingDAO;
        this.productDAO = productDAO;
    }

    @Override
    public ProductDetailsDTO byTcin(String id) {
        checkNotNull(id);
        ProductDetailsDTO result = new ProductDetailsDTO();
        try {
            ExternalProductResponseDAO externalProductResponseDAO = new ExternalProductResponseDAO();
            externalProductResponseDAO = productDAO.SearchByTcin(id);

            //If we get non 200 returncode, the description is not found, or the pricing is not found
            if (externalProductResponseDAO.getStatusCode() != HttpResponseStatus.OK.code() ||
                    externalProductResponseDAO.getProductDescription() == "" ||
                    this.pricingDAO.SearchByTcin(id) == null) {
                result.setReturnCode(HttpResponseStatus.NOT_FOUND.code());
                result.setProduct(null);
                return result;
            }

            Pricing pricing = this.pricingDAO.SearchByTcin(id);

            String initialDescription = externalProductResponseDAO.getProductDescription();
            Product product = new Product(id, initialDescription, pricing);

            result.setReturnCode(HttpResponseStatus.OK.code());
            result.setProduct(product);

        } catch (Exception ex) {
            log.error("ProductServiceImpl Class" + ex.getMessage());
        }
        return result;
    }
}
