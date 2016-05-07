package com.codeknowledge.model;

import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class CompanyStockValue {
	private String companyName;
	private BigDecimal price;
	
	public String getCompanyName() {
		return companyName;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setCampanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
