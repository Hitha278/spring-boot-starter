package com.domain.exchange.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.domain.exchange.dto.ExchangeRates;

/**
 * @author hithasree.mandapaka
 *
 */
public class CalculateUtil {
	
	/**
	 * @author hithasree.mandapaka
	 *
	 */
	public enum SortOrder {
		ASCENDING, DESCENDING, CONSTANT, UNDEFINED
	};
	
	/**
	 * @param endDate
	 * @return
	 */
	public static String getStartDate(String endDate) {
		String startDate = "";
		try {
			LocalDate requestedDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
			startDate = requestedDate.minusDays(7).toString();
		} catch (DateTimeParseException e) {
			startDate = endDate;
		}
		return startDate;
	}

	/**
	 * @param rates
	 * @param target
	 * @return
	 */
	public static String calculateAverage(ExchangeRates rates, String target) {
		String result = "";
		List<BigDecimal> averageRate = rates.getRates().entrySet().stream()
				.filter(s -> !s.getKey().equalsIgnoreCase(rates.getEnd_at()))
				.map(k -> new BigDecimal(k.getValue().get(target))).collect(Collectors.toList());
		result = averageRate.stream().reduce((a, b) -> a.add(b)).get().divide(new BigDecimal(averageRate.size()),5,RoundingMode.HALF_UP)
				.toString();

		return result;
	}

	/**
	 * @param rates
	 * @param target
	 * @return
	 */
	public static String getTrend(ExchangeRates rates, String target) {
		String result = SortOrder.UNDEFINED.toString();
		Map<LocalDate, Map<String, String>> averageRate = rates.getRates().entrySet().stream()
				.filter(s -> !s.getKey().equalsIgnoreCase(rates.getEnd_at())).collect(Collectors
						.toMap(k -> LocalDate.parse(k.getKey(), DateTimeFormatter.ISO_LOCAL_DATE), k -> k.getValue()));
		List<BigDecimal> orderedRates = averageRate.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.map(k -> new BigDecimal(k.getValue().get(target))).collect(Collectors.toList());

		if (isSorted(orderedRates, SortOrder.CONSTANT)) {
			result = SortOrder.CONSTANT.toString();
		} else if (isSorted(orderedRates, SortOrder.ASCENDING)) {
			result = SortOrder.ASCENDING.toString();
		} else if (isSorted(orderedRates, SortOrder.DESCENDING)) {
			result = SortOrder.DESCENDING.toString();
		}
		return result;
	}

	/**
	 * @param rates
	 * @param order
	 * @return
	 */
	private static boolean isSorted(List<BigDecimal> rates, SortOrder order) {
		if (rates.isEmpty() || rates.size() == 1) {
			return true;
		}

		Iterator<BigDecimal> iter = rates.iterator();
		BigDecimal current, previous = iter.next();
		while (iter.hasNext()) {
			current = iter.next();
			if ((order == SortOrder.CONSTANT && previous.compareTo(current) != 0)
					|| (order == SortOrder.ASCENDING && previous.compareTo(current) > 0)
					|| (order == SortOrder.DESCENDING && previous.compareTo(current) < 0)) {
				return false;
			}
			previous = current;
		}
		return true;
	}
}
