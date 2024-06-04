package com.cashify.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cashify.service.CoinInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/coin")
public class CoinInfoController {

	private static final Logger logger = LogManager.getLogger(CoinInfoController.class);

	@Autowired
	CoinInfoService coinInfoService;

	@GetMapping("/data")
	public ResponseEntity<String> getCoinData(@RequestParam String symbols)
			throws JsonMappingException, JsonProcessingException {
		logger.info("Inside CoinInfoController :: getCoinData()");
		String coinData = coinInfoService.getCoinData(symbols);
		return ResponseEntity.ok(coinData);
	}
}
