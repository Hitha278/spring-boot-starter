package com.domain.exchange.repo;

import org.springframework.data.repository.CrudRepository;

import com.domain.exchange.entity.ExchangeRateInfo;
import com.domain.exchange.entity.ExchangeRateInfoId;

public interface ExchangeRatesRepo extends CrudRepository<ExchangeRateInfo, ExchangeRateInfoId> {
}
