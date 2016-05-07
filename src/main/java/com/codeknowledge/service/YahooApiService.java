package com.codeknowledge.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeknowledge.model.CompanyStockValue;
import com.codeknowledge.model.CompanyStockValueDao;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service("yahooApiServiceManager")
public class YahooApiService implements YahooApiServiceManager{
	@Autowired
	CompanyStockValueDao companyStockValueDao;
	
	public CompanyStockValue getCompanyStockValue(String companyName) {
		String url = "http://finance.yahoo.com/webservice/v1/symbols/"+companyName+"/quote?format=json";		
		String data = connectToAPI(url,30000);
		JsonParser jsonParser = new JsonParser();
		JsonObject obj = jsonParser.parse(data).getAsJsonObject();
		BigDecimal price =(obj.get("list").getAsJsonObject().get("resources").getAsJsonArray().get(0).getAsJsonObject().get("resource").getAsJsonObject().get("fields").getAsJsonObject().get("price").getAsBigDecimal());
	    return companyStockValueDao.createNewCompanyStockValue(companyName,price);
	}
	
	
	public String connectToAPI(String url, int timeout) {
	    HttpURLConnection c = null;
	    try {
	        URL u = new URL(url);
	        c = (HttpURLConnection) u.openConnection();
	        c.setRequestMethod("GET");
	        c.setRequestProperty("Content-length", "0");
	        c.setUseCaches(false);
	        c.setAllowUserInteraction(false);
	        c.setConnectTimeout(timeout);
	        c.setReadTimeout(timeout);
	        c.connect();
	        int status = c.getResponseCode();

	        switch (status) {
	            case 200:
	            case 201:
	                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
	                StringBuilder sb = new StringBuilder();
	                String line;
	                while ((line = br.readLine()) != null) {
	                    sb.append(line+"\n");
	                }
	                br.close();
	                return sb.toString();
	        }

	    } catch (MalformedURLException ex) {
	        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
	        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
	    } finally {
	       if (c != null) {
	          try {
	              c.disconnect();
	          } catch (Exception ex) {
	             Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
	          }
	       }
	    }
	    return null;
	}
	
}
