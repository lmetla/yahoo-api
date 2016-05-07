package com.codeknowledge.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codeknowledge.model.CompanyStockValue;
import com.codeknowledge.service.YahooApiServiceManager;

@Controller
public class HomeController {
	static final String companyName = "BHP.AX";
	
	@Autowired
	YahooApiServiceManager yahooApiServiceManager;
	
	@RequestMapping(value ="/")
	public ModelAndView calculateTotalValue(@RequestParam Map<String,String> reqParam) {
		ModelAndView model = new ModelAndView("home");
		processRequest(model,reqParam);
		return model;
	}
	
	public ModelAndView processRequest(ModelAndView model, Map<String,String> reqParam) {
		
        CompanyStockValue companyStockValue = yahooApiServiceManager.getCompanyStockValue(companyName);
		model.addObject("companyStockValue", companyStockValue);
		if(reqParam.isEmpty())
		{
			model.addObject("units","0");
			model.addObject("totalValue","0");
		}
		else
		{
			model.addObject("units",reqParam.get("units"));
			model.addObject("totalValue",companyStockValue.getPrice().multiply(new BigDecimal(reqParam.get("units"))));
			
		}
		return model;
	}
}
