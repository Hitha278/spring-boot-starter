package com.domain.exchange.dto;

import java.util.Map;


/**
 * @author hithasree.mandapaka
 *
 */
public class ExchangeRates {

	/**
	 * 
	 */
	private Map<String,Map<String,String>> rates;
	
	/**
	 * 
	 */
	private String start_at;
	
	/**
	 * 
	 */
	private String base;
	
	/**
	 * 
	 */
	private String end_at;
	
	/**
	 * @return the rates
	 */
	public Map<String, Map<String, String>> getRates() {
		return rates;
	}

	/**
	 * @param rates the rates to set
	 */
	public void setRates(Map<String, Map<String, String>> rates) {
		this.rates = rates;
	}

	/**
	 * @return the start_at
	 */
	public String getStart_at() {
		return start_at;
	}

	/**
	 * @param start_at the start_at to set
	 */
	public void setStart_at(String start_at) {
		this.start_at = start_at;
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
	 * @return the end_at
	 */
	public String getEnd_at() {
		return end_at;
	}

	/**
	 * @param end_at the end_at to set
	 */
	public void setEnd_at(String end_at) {
		this.end_at = end_at;
	}

	
	
}
