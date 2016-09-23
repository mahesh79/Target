package com.myretail.app;


import com.myretail.handlers.ProductHandler;
import com.myretail.modules.ProductModule;
import com.myretail.utilities.DBSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;


public class Main {
    public final static String products_tcin = "myretail_products/v1/:tcin";

    private final static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String... args) throws Exception {
        DBSetup dbSetup = new DBSetup();
        dbSetup.InitialDataSetup();
        try {
            RatpackServer.start(
                server -> server.registry(Guice.registry(b -> b.module(ProductModule.class)))
                    .handlers(chain -> chain.get(products_tcin, ProductHandler.class)
                        .put(ProductHandler.class)));

        } catch (Exception e) {
            log.error("Main Class" + e.getMessage());
            e.printStackTrace();
        }
    }
}
