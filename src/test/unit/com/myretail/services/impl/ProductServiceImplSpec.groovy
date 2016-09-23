package com.myretail.services.impl

import com.myretail.daos.ExternalProductResponseDAO
import com.myretail.daos.PricingDAO
import com.myretail.daos.ProductDAO
import com.myretail.models.Pricing
import com.myretail.models.Product
import com.myretail.services.ProductDetailsDTO
import spock.lang.See
import spock.lang.Shared
import spock.lang.Specification
import io.netty.handler.codec.http.HttpResponseStatus

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static spock.util.matcher.HamcrestSupport.that

/**
 * ProductServiceImplSpec provides unit test specifications for ProductServiceImpl.
 */
class ProductServiceImplSpec extends Specification {

    @Shared
    Pricing samplePricing = new Pricing(
            "USD",
            new BigDecimal("23.1")
    )
    @Shared
    Product sampleProduct = new Product(
            "1233444",
            "item1",
            samplePricing
    )
    @Shared
    ProductDetailsDTO sampleProductDetails = new ProductDetailsDTO(HttpResponseStatus.OK.code(),
            sampleProduct
    )
    ExternalProductResponseDAO sampleExternalProductResponseDAO = new ExternalProductResponseDAO(
            HttpResponseStatus.OK.code(),
            sampleProduct.getName()
    )
    @See("Unit testCase - Product API Service")
    def "return value for an existing tcin"() {
        given:
            def mockProductDAO = Mock(ProductDAO)
            def mockPricingDAO = Mock(PricingDAO)
            def productService = new ProductServiceImpl(mockProductDAO, mockPricingDAO)
        when:
            def productDetailsDTO = productService.byTcin(sampleProduct.getid())
        then:
            2 * mockPricingDAO.SearchByTcin(sampleProduct.getid()) >> samplePricing
            1 * mockProductDAO.SearchByTcin(sampleProduct.getid())  >> sampleExternalProductResponseDAO
            that productDetailsDTO.returnCode, is(equalTo(HttpResponseStatus.OK.code()))
            that productDetailsDTO.product.getid(), is(equalTo(sampleProductDetails.product.getid()))
            that productDetailsDTO.product.getName(), is(equalTo(sampleProductDetails.product.getName()))
            that productDetailsDTO.product.pricing.getCurrencyCode(), is(equalTo(sampleProductDetails.product.pricing.getCurrencyCode()))
            that productDetailsDTO.product.pricing.getValue(), is(equalTo(sampleProductDetails.product.pricing.getValue()))


    }

    @See("Unit testCase - Product API Service")
    def " service returned a null value for tcin's not found "() {
        given:
            def mockProductDAO = Mock(ProductDAO)
            def mockPricingDAO = Mock(PricingDAO)
            def productService = new ProductServiceImpl(mockProductDAO, mockPricingDAO)
        when:
            def productDetailsDTO = productService.byTcin("TCIN_DOES_NOT_EXIST")
        then:
            1 * mockProductDAO.SearchByTcin("TCIN_DOES_NOT_EXIST") >> null
}

}