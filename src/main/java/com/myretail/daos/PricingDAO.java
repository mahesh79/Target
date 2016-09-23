package com.myretail.daos;

import com.myretail.models.Pricing;

/**
 * PricingDAO defines the persistence functionality for a Pricing within My Retail.
 */

public interface PricingDAO {

    /**
     * Used to find a persisted Pricing given it's unique database identifier.
     *
     * @param tcin the unique database identifier of a product
     *
     * @return @return a persisted Pricing or NULL if one is not found for the given id.
     */
    Pricing SearchByTcin(String id);

}
