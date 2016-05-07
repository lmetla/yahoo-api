package com.codeknowledge.model;

import java.math.BigDecimal;

public interface CompanyStockValueDao {
	
	public CompanyStockValue createNewCompanyStockValue(String companyName,BigDecimal price);
}
