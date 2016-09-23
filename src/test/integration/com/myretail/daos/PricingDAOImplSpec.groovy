package com.myretail.daos

import com.myretail.models.Pricing;


import spock.lang.See
import spock.lang.Shared
import spock.lang.Specification

import static org.hamcrest.Matchers.comparesEqualTo
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static spock.util.matcher.HamcrestSupport.that


class PricingDAOImplSpec extends Specification {

    PricingDAOImpl pricingDAOImpl = new PricingDAOImpl()

    def "Find the expected TCIN from in-memory mongoDB"() {
        when:
        Pricing pricing = pricingDAOImpl.SearchByTcin("13860428")

        then:
        that pricing.currencyCode, is(equalTo("USD"))
        that pricing.value, is(comparesEqualTo((new Double("123.3"))))
    }

    def "the unexpected TCIN from in-memory mongoDB should yield null object"() {
        when:
        Pricing pricing = pricingDAOImpl.SearchByTcin("TCIN_NOT_IN_DB")

        then:
        assert pricing == null
    }

}
