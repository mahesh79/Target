package com.myretail.daos;

import com.mongodb.*;
import com.myretail.models.Pricing;
import com.myretail.utilities.DBSetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import java.util.List;


public class PricingDAOImpl implements PricingDAO {

    private final static Logger log = LoggerFactory.getLogger(PricingDAOImpl.class);
    @Inject
    public PricingDAOImpl() {

    }

    public Pricing SearchByTcin(String id) {

        Pricing pricing = null;
        MongoClient mongoClient = null;
        DBSetup dbSetup = new DBSetup();

        try {
            /**** Search Query by Id ****/
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("id", id);
            mongoClient = dbSetup.CreateDbConnection();
            // if database doesn't exists, MongoDB will create one
            if (mongoClient.getDatabaseNames().contains("myretailDB") == true) {
                DB db = mongoClient.getDB("myretailDB");
                // if collection doesn't exists, MongoDB will create one
                DBCollection table = db.getCollection("ProductPricing");
                List<DBObject> myList = null;
                DBCursor cursor = table.find(searchQuery).limit(1);
                myList = cursor.toArray();
                if (!myList.isEmpty() )
                pricing = new Pricing(myList.get(0).get("currency_code").toString(),
                        (double) myList.get(0).get("value"));
            }
        }  catch (MongoException e) {
            log.error("PricingDAOImpl Class" + e.getMessage());
        }
        finally {
            mongoClient.close();
        }
        return pricing;
    }
}