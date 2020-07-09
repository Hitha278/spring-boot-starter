package com.domain.exchange.controller;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.exchange.dto.ExchangeRateInfoDTO;
import com.domain.exchange.dto.response.Response;
import com.domain.exchange.exception.BKException;
import com.domain.exchange.exception.ExceptionType;
import com.domain.exchange.exception.VariableType;
import com.domain.exchange.services.ExchangeRateService;
import com.domain.exchange.util.ValidationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author hithasree.mandapaka
 *
 */
@RestController
@RequestMapping("/exchange-rate")
@Api(value = "MainController, REST APIs that deal with Exchange Rates info")
public class MainController {

	/**
	 * The Exchange Rate Service
	 */
	@Autowired
	private ExchangeRateService service;

	/**
	 * @param date
	 * @param base
	 * @param target
	 * @return responseDTO
	 */
	@GetMapping("/{date}/{baseCurrency}/{targetCurrency}")
	@ApiOperation(value = "Fetches required info for provided BaseCurrency and Target Currency", response = ExchangeRateInfoDTO.class, tags = "displayExchangeInfo")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched the exchange rates info successfully"),
			@ApiResponse(code = 404, message = "Exchange details not found") })
	public Response<ExchangeRateInfoDTO> displayExchangeRates(
			@PathVariable(value = "date", required = true) String date,
			@PathVariable(value = "baseCurrency", required = true) String base,
			@PathVariable(value = "targetCurrency", required = true) String target) {

		try {
			validateRequest(date, base, target);
			Response<ExchangeRateInfoDTO> response = Response.ok();
			response.setPayload(service.getExchangeRateInfo(date, base, target));
			return response;
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error occured while forming external URL");
		}

	}

	private void validateRequest(String date, String baseCur, String targetCur) {

		if (!ValidationUtil.IS_VALID_DATE_FORMAT.test(date)) {
			throw BKException.throwExceptionWithId(VariableType.REQUESTED_DATE, ExceptionType.VALIDATION_EXCEPTION,
					"Requested date data '%s' does not match format {yyyy-MM-dd}", date);
		} else if (!ValidationUtil.IS_VALID_DATE.test(date)) {
			throw BKException.throwExceptionWithId(VariableType.REQUESTED_DATE, ExceptionType.VALIDATION_EXCEPTION,
					"Requested Date data '%s' is not between between 2000-01-01 and yesterday", date);
		} else if (!ValidationUtil.IS_VALID_CURRENCY.test(baseCur)) {
			throw BKException.throwExceptionWithId(VariableType.BASE_CURRENCY, ExceptionType.VALIDATION_EXCEPTION,
					"Base Currency {%s} is not valid", baseCur);
		} else if (!ValidationUtil.IS_VALID_CURRENCY.test(targetCur)) {
			throw BKException.throwExceptionWithId(VariableType.TARGET_CURRENCY, ExceptionType.VALIDATION_EXCEPTION,
					"Target Currency {%s} is not valid", targetCur);
		} else if (baseCur.equalsIgnoreCase(targetCur)) {
			throw BKException.throwExceptionWithId(VariableType.TARGET_CURRENCY, ExceptionType.VALIDATION_EXCEPTION,
					"Base Currency {%s} and Target Currency {%s} cannot be same", baseCur, targetCur);
		}
	}

}
