package com.info.service;

import java.util.ArrayList;
import java.util.List;

public class TestPortFolioService {
	
	
	static PortfolioService portfolioService=new PortfolioService();
	
	public static void main(String[] args) {

		String label[] = new String[] { "Bonds", "Large Cap", "Mid Cap", "Foreign", "Small Cap" };

		List<String> result = new ArrayList();
		double[] difference = new double[] { 8399.6, 100.15, -899.85, -2599.9, -5000 };

		portfolioService.minimizeTransactions(difference, result, label);
		for (String str : result)
			System.out.println(str);

	}

}
