package com.domain.exchange.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author hithasree.mandapaka
 *
 */
public class ValidationUtil {

	/**
	 * 
	 */
	private static final List<String> CURRENCIES = new ArrayList<>(Arrays.asList("HKD", "ISK", "PHP", "DKK", "HUF",
			"CZK", "AUD", "RON", "SEK", "IDR", "INR", "BRL", "RUB", "HRK", "JPY", "THB", "CHF", "SGD", "PLN", "BGN",
			"TRY", "CNY", "NOK", "NZD", "ZAR", "USD", "MXN", "ILS", "GBP", "KRW", "MYR", "EUR"));
	/**
	 * 
	 */
	public static final Predicate<String> IS_VALID_CURRENCY = s -> CURRENCIES.contains(s);
	/**
	 * 
	 */
	public static final Predicate<String> IS_VALID_DATE_FORMAT = s -> {
		try {
			return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE) != null;
		} catch (DateTimeParseException e) {
			return false;
		}
	};
	/**
	 * 
	 */
	public static final Predicate<String> IS_VALID_DATE = s -> {
		try {
			LocalDate date = LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
			return date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.of(2000, 01, 01));
		} catch (DateTimeParseException e) {
			return false;
		}
	};
	/**
	 * 
	 */
	public static final Predicate<String> IS_WEEKEND = s -> {
		try {
			LocalDate date = LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
			return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
		} catch (DateTimeParseException e) {
			return false;
		}
	};

}
