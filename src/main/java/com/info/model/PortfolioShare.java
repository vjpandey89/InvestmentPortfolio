package com.info.model;

import org.springframework.stereotype.Repository;




/**
 * @author vijay
 *
 *Model class for PortfolioShare percentage
 *
 */
@Repository
public class PortfolioShare {

	private double bondsShare;
	private double largeCapShare;
	private double midCapShare;
	private double foreignShare;
	private double smallCapShare;

	public double getBondsShare() {
		return bondsShare;
	}

	public void setBondsShare(double bondsShare) {
		this.bondsShare = bondsShare;
	}

	public double getLargeCapShare() {
		return largeCapShare;
	}

	public void setLargeCapShare(double largeCapShare) {
		this.largeCapShare = largeCapShare;
	}

	public double getMidCapShare() {
		return midCapShare;
	}

	public void setMidCapShare(double midCapShare) {
		this.midCapShare = midCapShare;
	}

	public double getForeignShare() {
		return foreignShare;
	}

	public void setForeignShare(double foreignShare) {
		this.foreignShare = foreignShare;
	}

	public double getSmallCapShare() {
		return smallCapShare;
	}

	public void setSmallCapShare(double smallCapShare) {
		this.smallCapShare = smallCapShare;
	}

}
