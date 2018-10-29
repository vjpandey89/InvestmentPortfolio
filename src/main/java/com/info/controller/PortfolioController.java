package com.info.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.model.Portfolio;
import com.info.model.PortfolioShare;
import com.info.service.PortfolioService;

/**
 * @author vijay
 * 
 * Centralized controller/Resource manager with end points redirecting to appropriate service methods
 *
 */
@RestController
@Validated
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;

	@RequestMapping(method = RequestMethod.GET, value = "/portfolio/{riskPreference}")
	public ResponseEntity<PortfolioShare> getPortfolio( @PathVariable  int riskPreference) {

		
		return new ResponseEntity<>(portfolioService.getPortfolioByRiskPreference(riskPreference),HttpStatus.OK);
		
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/portfolio/{riskPreference}")
	public ResponseEntity<Portfolio> getRecommendedPortfolio(@RequestBody @Valid  Portfolio portfolio,
			@PathVariable int riskPreference) {

		portfolio = portfolioService.getRecommendedPortfolio(riskPreference, portfolio);
		return new ResponseEntity<>(portfolio, HttpStatus.OK);

	}

}
