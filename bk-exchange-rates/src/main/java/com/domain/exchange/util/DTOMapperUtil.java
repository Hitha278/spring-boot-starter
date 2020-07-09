package com.domain.exchange.util;

import com.domain.exchange.dto.ExchangeRateInfoDTO;
import com.domain.exchange.entity.ExchangeRateInfo;

/**
 * @author hithasree.mandapaka
 *
 */
public class DTOMapperUtil {
	
	/**
	 * @param info
	 * @return
	 */
	public static ExchangeRateInfoDTO toResponseDTO(ExchangeRateInfo info) {
		ExchangeRateInfoDTO result=new ExchangeRateInfoDTO();
		result.setRate(info.getRate());
		result.setAverage(info.getAverage());
		result.setTrend(info.getTrend());		
		return result;
		
	}

}
