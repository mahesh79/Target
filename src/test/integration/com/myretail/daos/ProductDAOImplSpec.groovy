package com.myretail.daos

import com.google.inject.Inject
import com.myretail.models.Pricing;
import com.myretail.utilities.DBSetup;

import spock.lang.See
import spock.lang.Shared
import spock.lang.Specification
import io.netty.handler.codec.http.HttpResponseStatus
import spock.lang.Unroll

import static org.hamcrest.Matchers.comparesEqualTo
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static spock.util.matcher.HamcrestSupport.that


class ProductDAOImplSpec extends Specification {

    ProductDAOImpl productDAOImpl = new ProductDAOImpl()

    @Unroll
    def "validate varied response for given tcin from external product service API"() {
        when:
        ExternalProductResponseDAO externalProductResponseDAO = productDAOImpl.SearchByTcin(id)

        then:
        that externalProductResponseDAO.statusCode, is(equalTo(statuscode))
        that externalProductResponseDAO.productDescription, is(equalTo(productdescription))

        where:
        id               | productdescription          || statuscode
        "13860428"       | "BIG LEBOWSKI, THE Blu-ray" || HttpResponseStatus.OK.code()
        "NOT_VALID_TCIN" | null                        || 0
    }

}
