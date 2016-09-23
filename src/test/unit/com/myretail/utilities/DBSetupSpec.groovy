package com.myretail.utilities

import com.myretail.utilities.DBSetup
import com.mongodb.*
import spock.lang.Specification
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.See

class DBSetupSpec extends Specification {


    def "new DB client should be created"() {

        given:
        def dbSetup = new DBSetup()

        when:
        def testMongoClient = dbSetup.CreateDbConnection()

        then:
        assert testMongoClient != null

    }

    def "ensure ProductPricing DB and documents are setup right"() {

        given:
        def dbSetup = new DBSetup()
        def testMongoClient = dbSetup.CreateDbConnection();

        when:
        dbSetup.InitialDataSetup();

        then:
        assert testMongoClient.getDatabaseNames().contains("myretailDB") == true
        assert testMongoClient.getAddress().host == "localhost"
        assert testMongoClient.getAddress().port == 27017
    }
}
