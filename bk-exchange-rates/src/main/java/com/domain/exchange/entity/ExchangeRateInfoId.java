package com.domain.exchange.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ExchangeRateInfoId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExchangeRateInfoId() {
	}
	
	public ExchangeRateInfoId(String requestedDate, String baseCurrency, String targetCurrency) {
		this.requestedDate=requestedDate;
		this.baseCurrency=baseCurrency;
		this.targetCurrency=targetCurrency;
	}

	/**
	 * 
	 */
	private String requestedDate;
	
	/**
	 * 
	 */	
	private String baseCurrency;
	
	
	/**
	 * 
	 */
	private String targetCurrency;
	

	/**
	 * @return the requestedDate
	 */
	public String getRequestedDate() {
		return requestedDate;
	}


	/**
	 * @param requestedDate the requestedDate to set
	 */
	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}


	/**
	 * @return the baseCurrency
	 */
	public String getBaseCurrency() {
		return baseCurrency;
	}


	/**
	 * @param baseCurrency the baseCurrency to set
	 */
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}


	/**
	 * @return the targetCurrency
	 */
	public String getTargetCurrency() {
		return targetCurrency;
	}


	/**
	 * @param targetCurrency the targetCurrency to set
	 */
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
}
