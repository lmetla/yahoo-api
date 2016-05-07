package com.codeknowledge.service;

import com.codeknowledge.model.CompanyStockValue;

public interface YahooApiServiceManager {
	
	public CompanyStockValue getCompanyStockValue(String data);
}
