package com.myretail.daos;


public interface ProductDAO {

    ExternalProductResponseDAO SearchByTcin(String id);
}
