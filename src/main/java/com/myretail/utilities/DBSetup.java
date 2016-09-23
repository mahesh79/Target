package com.myretail.utilities;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBSetup {
    private final static Logger log = LoggerFactory.getLogger(DBSetup.class);
    public DBSetup() {}
    public MongoClient CreateDbConnection() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient("localhost", 27017);
        } catch (UnknownHostException ex) {
            log.error(ex.getMessage());
        } catch (MongoException ex) {
            log.error(ex.getMessage());
        }
        return mongoClient;
    }

    public void InitialDataSetup() {
        MongoClient mongoClient = null;
        try {
            mongoClient = CreateDbConnection();
            // if database doesn't exists, MongoDB will create one
            if (mongoClient.getDatabaseNames().contains("myretailDB") == true) {
                mongoClient.dropDatabase("myretailDB");
            }
            DB db = mongoClient.getDB("myretailDB");
            // if collection doesn't exists, MongoDB will create one
            DBCollection table = db.getCollection("ProductPricing");
            // key value document
            BasicDBObject document = new BasicDBObject();

            //Push product 1
            document.put("id", "13860428");
            document.put("currency_code", "USD");
            document.put("value", 123.3);
            table.insert(document);
            document.clear();

            //Push product 2
            document.put("id", "51201370");
            document.put("currency_code", "USD");
            document.put("value", 231.2);
            table.insert(document);
            document.clear();

            //Push product 3
            document.put("id", "51201790");
            document.put("currency_code", "EURO");
            document.put("value", 333.5);
            table.insert(document);
            document.clear();

        } catch (MongoException ex) {
            log.error(ex.getMessage());
        } finally {
           mongoClient.close();
        }
    }

}
