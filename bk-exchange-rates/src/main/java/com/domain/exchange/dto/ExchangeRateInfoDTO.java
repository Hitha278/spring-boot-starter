package com.domain.exchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * @author hithasree.mandapaka
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateInfoDTO {

	/**
	 * 
	 */
	@JsonProperty("exchange_rate")
	private String rate;
	
	/**
	 * 
	 */
	@JsonProperty("average")
	private String average;
	
	/**
	 * 
	 */
	@JsonProperty("exchange-trend")
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
	
}
