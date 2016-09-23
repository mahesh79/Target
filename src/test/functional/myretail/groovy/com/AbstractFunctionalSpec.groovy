package com.myretail

import com.myretail.app.Main
import org.apache.commons.lang3.StringUtils
import ratpack.test.ApplicationUnderTest
import ratpack.test.MainClassApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Shared
import spock.lang.Specification


class AbstractFunctionalSpec extends Specification {

    @Shared
    String portValue = "5050"

    @Shared
    String ipAddress = "http://localhost"

    @Shared
    String url = constructURL() //dev node URL

    //MainClassApplicationUnderTest test = new MainClassApplicationUnderTest(Main.class)
    @Shared
    ApplicationUnderTest aut = applicationUnderTest()
    @Delegate
    TestHttpClient testClient = testHttpClient(aut)

    def constructURL() {
        if (ipAddress == null || ipAddress.isEmpty()) {
            url = "";
        } else {
            url = (ipAddress + ":" + portValue);
        }
    }

    def setup() {
        resetRequest()
    }

    def getItem(String id) {
        get(id)
    }

    def cleanupSpec() {
        if (StringUtils.isEmpty(url)) {
            ((MainClassApplicationUnderTest) aut).stop();
        }
        // test.close()
    }

    def ApplicationUnderTest applicationUnderTest() {
        ApplicationUnderTest applicationUnderTest
        if (StringUtils.isNotEmpty(url)) {
            println url
            applicationUnderTest = {
                new URI(url.endsWith('/') ? url : url + '/')
            } as ApplicationUnderTest
        } else {
            applicationUnderTest = new MainClassApplicationUnderTest(Main.class)
        }
        applicationUnderTest
    }
}