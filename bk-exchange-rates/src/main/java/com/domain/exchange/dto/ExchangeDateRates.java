package com.domain.exchange.dto;

import java.util.Map;

public class ExchangeDateRates {

	private Map<String,String> rates;
	
	private String base;
	
	private String date;

	/**
	 * @return the rates
	 */
	public Map<String, String> getRates() {
		return rates;
	}

	/**
	 * @param rates the rates to set
	 */
	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}

	/**
	 * @return the base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
