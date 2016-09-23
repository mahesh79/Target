package com.myretail

import groovy.json.JsonSlurper
import io.netty.handler.codec.http.HttpResponseStatus
import org.apache.http.HttpStatus
import spock.lang.See
import spock.lang.Unroll
import spock.lang.AutoCleanup

import java.lang.reflect.Array


class MyRetailServices extends com.myretail.AbstractFunctionalSpec {

    def "validate the myretail product API service response data by searching by tcin"() {
        given:
        def response
        String webTestBase = url + "/myretail_products/v1/"

        JsonSlurper jSlurper = new JsonSlurper()

        when: "calling the Product API"
        String validURI = webTestBase + tcin
        response = getItem(validURI)

        then: "response data should match the expected value"
        def list = jSlurper.parseText(response.body.text)
        assert HttpResponseStatus.OK.code() == response.statusCode
        Map map = (Map) list
        Map mapprice = (Map) map.get("pricing")

        assert map.get("name") == name
        assert mapprice.get("currency_code") == currency_code
        assert mapprice.get("value") == value


        where:
         tcin      || name                                    ||  currency_code ||  value
        "13860428" || "BIG LEBOWSKI, THE Blu-ray"             ||  "USD"         || 123.3


    }

    @Unroll
    @See("Functional testCase - Product API Service")
    def "validate the product API service response codes, search by tcin"() {
        given:
        def response
        String webTestBase = url + "/myretail_products/v1/"

        when: "Calling the myretail product API service with different condition"
        String validURI = webTestBase + tcin
        response = getItem(validURI)

        then: "Validate the Service Response Code for different conditions in where block"
        expected_Result.code() == response.statusCode

        where:
        condition      | tcin       || expected_Result
        "Valid input"  | "13860428" || HttpResponseStatus.OK
        "empty tcin"   | ""         || HttpResponseStatus.NOT_FOUND
        "Invalid tcin" | "asdas"    || HttpResponseStatus.BAD_REQUEST

    }

}
