package com.cashify.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface CoinInfoService {

	String getCoinData(String symbols) throws JsonMappingException, JsonProcessingException;

}
