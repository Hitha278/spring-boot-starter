package com.domain.exchange.util;

public interface BKConstants {
	/**
	 * 
	 */
	public static final String EXCHANGE_HISTORY_URL = "https://api.exchangeratesapi.io/history?start_at={start_at}&end_at={end_at}&base={base}&symbols={target}";
	
	/**
	 * 
	 */
	public static final String EXCHANGE_DATE_URL = "https://api.exchangeratesapi.io/%s?base={base}&symbols={target}";

	public static final String START_AT = "start_at";

	public static final String END_AT = "end_at";

	public static final String BASE = "base";

	public static final String TARGET = "target";



}
