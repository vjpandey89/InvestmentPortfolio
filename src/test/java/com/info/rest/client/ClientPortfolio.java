package com.info.rest.client;



public class ClientPortfolio {

	private double bondsAmount;

	private double largeCapAmount;

	private double midCapAmount;

	private double foreignAmount;

	private double smallCapAmount;

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

}
