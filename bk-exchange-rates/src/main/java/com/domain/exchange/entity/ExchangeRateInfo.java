package com.domain.exchange.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ExchangeRateInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private ExchangeRateInfoId infoId;
	
	/**
	 * 
	 */
	private String rate;
	
	/**
	 * 
	 */
	private String average;
	
	/**
	 * 
	 */
	private String trend;
	
	
	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}


	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}


	/**
	 * @return the average
	 */
	public String getAverage() {
		return average;
	}


	/**
	 * @param average the average to set
	 */
	public void setAverage(String average) {
		this.average = average;
	}


	/**
	 * @return the trend
	 */
	public String getTrend() {
		return trend;
	}


	/**
	 * @param trend the trend to set
	 */
	public void setTrend(String trend) {
		this.trend = trend;
	}


	/**
	 * @return the infoId
	 */
	public ExchangeRateInfoId getInfoId() {
		return infoId;
	}


	/**
	 * @param infoId the infoId to set
	 */
	public void setInfoId(ExchangeRateInfoId infoId) {
		this.infoId = infoId;
	}


}
