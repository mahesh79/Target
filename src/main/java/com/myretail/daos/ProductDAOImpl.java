package com.myretail.daos;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProductDAOImpl implements ProductDAO{

    private final static Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);
    private final String USER_AGENT = "Mozilla/5.0";

    public ProductDAOImpl() {
    }

    // HTTP GET request
    public ExternalProductResponseDAO SearchByTcin(String id) {

        String url = "https://api.target.com/products/v3/" + id + "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
        String description = "";
        String inputLine;
        StringBuffer response = new StringBuffer();
        ExternalProductResponseDAO externalProductResponseDAO = new ExternalProductResponseDAO();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject data = jsonObject.getJSONObject("product_composite_response");
            JSONArray items = data.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                description = items.getJSONObject(i).getString("general_description");
            }
            externalProductResponseDAO.setStatusCode(responseCode);
            externalProductResponseDAO.setProductDescription(description);
        } catch (Exception ex) {
            log.error("PricingDAOImpl Class" + ex.getMessage());
        }
        return externalProductResponseDAO;

    }
}