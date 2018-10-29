package com.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.dto.PortfolioLoader;

import com.info.exceptions.RiskPreferenceNotValidException;
import com.info.model.Portfolio;
import com.info.model.PortfolioShare;
import com.info.util.PrecisionHelper;



/**
 * @author vijay
 *
 *Service class to handle the business logic
 *
 */
@Service
public class PortfolioService {

	@Autowired
	private PortfolioLoader portfolioLoader;

	@Autowired
	private PortfolioShare portfolioShares;

	/**
	 *Executes the first part of requirement. 
	 *i.e. gets the portfolio shared percentage for a risk preference
	 *
	 *
	 */
	
	public PortfolioShare getPortfolioByRiskPreference(int riskPreference) {

		if (!validate(riskPreference))
			throw new RiskPreferenceNotValidException("Invalid Risk Preference");

		portfolioShares.setBondsShare(Double.parseDouble(portfolioLoader.bonds[riskPreference - 1]));
		portfolioShares.setLargeCapShare(Double.parseDouble(portfolioLoader.largeCap[riskPreference - 1]));
		portfolioShares.setMidCapShare(Double.parseDouble(portfolioLoader.midCap[riskPreference - 1]));
		portfolioShares.setForeignShare(Double.parseDouble(portfolioLoader.foreign[riskPreference - 1]));
		portfolioShares.setSmallCapShare(Double.parseDouble(portfolioLoader.smallCap[riskPreference - 1]));

		return portfolioShares;
	}

	
	/**
	 *Executes the second part of requirement. 
	 *i.e. gets the recommended portfolio also with the number of transfers neede
	 *
	 */
	public Portfolio getRecommendedPortfolio(int riskPreference, Portfolio portfolio) {

		portfolioShares = getPortfolioByRiskPreference(riskPreference);
		double totalInvestment;

		double[] difference = new double[5];

		totalInvestment = portfolio.getTotalInvestMent();

		difference[0] = portfolio.getBondsAmount() - ((portfolioShares.getBondsShare() * totalInvestment) / 100);
		difference[1] = portfolio.getLargeCapAmount() - ((portfolioShares.getLargeCapShare() * totalInvestment) / 100);
		difference[2] = portfolio.getMidCapAmount() - ((portfolioShares.getMidCapShare() * totalInvestment) / 100);
		difference[3] = portfolio.getForeignAmount() - ((portfolioShares.getForeignShare() * totalInvestment) / 100);
		difference[4] = portfolio.getSmallCapAmount() - ((portfolioShares.getSmallCapShare() * totalInvestment) / 100);

		minimizeTransactions(difference, portfolio.getResults(), portfolio.getLabels());

		portfolio.setBondsAmount(PrecisionHelper.round((portfolioShares.getBondsShare() * totalInvestment) / 100));
		portfolio.setLargeCapAmount(PrecisionHelper.round((portfolioShares.getLargeCapShare() * totalInvestment) / 100));
		portfolio.setMidCapAmount(PrecisionHelper.round((portfolioShares.getMidCapShare() * totalInvestment) / 100));
		portfolio.setForeignAmount(PrecisionHelper.round((portfolioShares.getForeignShare() * totalInvestment) / 100));
		portfolio.setSmallCapAmount(PrecisionHelper.round((portfolioShares.getSmallCapShare() * totalInvestment) / 100));

		return portfolio;
	}

	
	
	/**
	 *Method which has the logic for minimizing the transaction to reach to model portfolio
	 *
	 */
	public void minimizeTransactions(double[] difference, List<String> result, String label[]) {

		int maxWithdraw = getMax(difference), maxDeposit = getMin(difference);

		if (difference[maxWithdraw] < 1 && difference[maxDeposit] < 1)
			return;

		double min = Math.abs(difference[maxWithdraw]) < Math.abs(difference[maxDeposit])
				? Math.abs(difference[maxWithdraw])
				: Math.abs(difference[maxDeposit]);

		difference[maxWithdraw] -= min;
		difference[maxDeposit] += min;

		result.add("Transfer " + PrecisionHelper.round(min) + " from " + label[maxWithdraw] + " to " + label[maxDeposit]);

		minimizeTransactions(difference, result, label);

	}

	int getMin(double difference[]) {
		int minInd = 0;
		for (int i = 1; i < difference.length; i++)
			if (difference[i] < difference[minInd])
				minInd = i;
		return minInd;
	}

	int getMax(double difference[]) {
		int maxInd = 0;
		for (int i = 1; i < difference.length; i++)
			if (difference[i] > difference[maxInd])
				maxInd = i;
		return maxInd;
	}

	public boolean validate(int riskPreference) {
		return riskPreference >= 1 && riskPreference <= 10;
	}

}
