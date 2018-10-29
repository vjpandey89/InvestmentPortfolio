package com.info.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 * @author vijay
 *
 *Model class for Portfolio
 */
@Component
public class Portfolio {

	@NotNull
	@Min(0)
	@Max(Long.MAX_VALUE)
	@Digits(fraction = 2, integer = 10)
	private double bondsAmount;
	@NotNull
	@Min(0)
	@Max(Long.MAX_VALUE)
	private double largeCapAmount;
	@NotNull
	@Min(0)
	@Max(Long.MAX_VALUE)
	private double midCapAmount;
	@NotNull
	@Min(0)
	@Max(Long.MAX_VALUE)
	private double foreignAmount;
	@NotNull
	@Min(0)
	@Max(Long.MAX_VALUE)
	private double smallCapAmount;

	private List<String> results = new ArrayList();

	public List<String> getResults() {
		return results;
	}

	public void setResults(List<String> results) {
		this.results = results;
	}

	public double getBondsAmount() {
		return bondsAmount;
	}

	public void setBondsAmount(double bondsAmount) {
		this.bondsAmount = bondsAmount;
	}

	public double getLargeCapAmount() {
		return largeCapAmount;
	}

	public void setLargeCapAmount(double largeCapAmount) {
		this.largeCapAmount = largeCapAmount;
	}

	public double getMidCapAmount() {
		return midCapAmount;
	}

	public void setMidCapAmount(double midCapAmount) {
		this.midCapAmount = midCapAmount;
	}

	public double getForeignAmount() {
		return foreignAmount;
	}

	public void setForeignAmount(double foreignAmount) {
		this.foreignAmount = foreignAmount;
	}

	public double getSmallCapAmount() {
		return smallCapAmount;
	}

	public void setSmallCapAmount(double smallCapAmount) {
		this.smallCapAmount = smallCapAmount;
	}

	public double getTotalInvestMent() {
		return bondsAmount + largeCapAmount + midCapAmount + foreignAmount + smallCapAmount;
	}

	@JsonIgnore
	public String[] getLabels() {
		return new String[] { "Bonds", "Large Cap", "Mid Cap", "Foreign", "Small Cap" };
	}

}
