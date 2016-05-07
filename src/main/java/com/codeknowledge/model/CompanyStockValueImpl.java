package com.codeknowledge.model;

import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

@Repository ("CompanyStockValueDao")
public class CompanyStockValueImpl implements CompanyStockValueDao{

	@Override
	public CompanyStockValue createNewCompanyStockValue(String companyName,BigDecimal price) {
		CompanyStockValue companyStockValue = new CompanyStockValue();
		companyStockValue.setCampanyName(companyName);
		companyStockValue.setPrice(price);
		return companyStockValue;
	}

}
