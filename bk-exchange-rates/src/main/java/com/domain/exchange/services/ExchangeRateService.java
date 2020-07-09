package com.domain.exchange.services;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.domain.exchange.dto.ExchangeDateRates;
import com.domain.exchange.dto.ExchangeRateInfoDTO;
import com.domain.exchange.dto.ExchangeRates;
import com.domain.exchange.entity.ExchangeRateInfo;
import com.domain.exchange.entity.ExchangeRateInfoId;
import com.domain.exchange.repo.ExchangeRatesRepo;
import com.domain.exchange.util.BKConstants;
import com.domain.exchange.util.CalculateUtil;
import com.domain.exchange.util.DTOMapperUtil;

/**
 * @author hithasree.mandapaka
 *
 */
@Service
public class ExchangeRateService {

	

	/**
	 * 
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 
	 */
	@Autowired
	private ExchangeRatesRepo repo;

	// https://api.exchangeratesapi.io/history?start_at=2020-03-06&end_at=2020-03-13&base=USD&symbols=EUR
	/**
	 * @param date
	 * @param baseCur
	 * @param targetCur
	 * @return
	 * @throws MalformedURLException
	 */
	public ExchangeRateInfoDTO getExchangeRateInfo(String date, String baseCur, String targetCur) throws MalformedURLException {
		ExchangeRateInfoDTO result = new ExchangeRateInfoDTO();
		ExchangeRateInfoId requestID=new ExchangeRateInfoId(date, baseCur, targetCur);
		if(repo.existsById(requestID)) {
			result=DTOMapperUtil.toResponseDTO(repo.findById(requestID).get());
		}else {
			result=getInfoFromApi(date, baseCur, targetCur);
			persistUserRequest(result, date, baseCur, targetCur);
		}
		
		return result;
	}
	
	/**
	 * @param date
	 * @param baseCur
	 * @param targetCur
	 * @return
	 */
	private ExchangeRateInfoDTO getInfoFromApi(String date, String baseCur, String targetCur) {
		ExchangeRateInfoDTO result = new ExchangeRateInfoDTO();
		ExchangeDateRates rates= getRateInfoFromApi(date, baseCur, targetCur);
		ExchangeRates historyRates=getHistoryInfoFromApi(rates.getDate(), baseCur, targetCur);
		result.setRate(rates.getRates().get(targetCur));
		result.setAverage(CalculateUtil.calculateAverage(historyRates, targetCur));
		result.setTrend(CalculateUtil.getTrend(historyRates, targetCur));		
		return result;
	}

	/**
	 * @param result
	 * @param date
	 * @param baseCur
	 * @param targetCur
	 */
	private void persistUserRequest(ExchangeRateInfoDTO result,String date, String baseCur, String targetCur) {
		
		ExchangeRateInfo request= new ExchangeRateInfo();
		
		request.setAverage(result.getAverage());
		request.setRate(result.getRate());
		request.setTrend(result.getTrend());
		request.setInfoId(new ExchangeRateInfoId(date, baseCur, targetCur));
		repo.save(request);
	}
	
	/**
	 * @param reqDate
	 * @param baseCur
	 * @param targetCur
	 * @return
	 */
	private ExchangeRates getHistoryInfoFromApi(String reqDate, String baseCur, String targetCur) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put(BKConstants.START_AT, CalculateUtil.getStartDate(reqDate));
		uriVariables.put(BKConstants.END_AT, reqDate);
		uriVariables.put(BKConstants.BASE, baseCur);
		uriVariables.put(BKConstants.TARGET, targetCur);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<ExchangeRates> response = restTemplate.exchange(
				UriComponentsBuilder.fromHttpUrl(BKConstants.EXCHANGE_HISTORY_URL).buildAndExpand(uriVariables).encode().toUri(),
				HttpMethod.GET, requestEntity, ExchangeRates.class);
		return response.getBody();
	}

	/**
	 * @param reqDate
	 * @param baseCur
	 * @param targetCur
	 * @return
	 */
	private ExchangeDateRates getRateInfoFromApi(String reqDate, String baseCur, String targetCur) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put(BKConstants.BASE, baseCur);
		uriVariables.put(BKConstants.TARGET, targetCur);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<ExchangeDateRates> response = restTemplate.exchange(
				UriComponentsBuilder.fromHttpUrl(String.format(BKConstants.EXCHANGE_DATE_URL,reqDate)).buildAndExpand(uriVariables).encode().toUri(),
				HttpMethod.GET, requestEntity, ExchangeDateRates.class);
		return response.getBody();
	}

}
