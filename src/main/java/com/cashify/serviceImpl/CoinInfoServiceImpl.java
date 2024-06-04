package com.cashify.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cashify.model.CryptoCurrency;
import com.cashify.model.Quote;
import com.cashify.repo.CryptoCurrencyRepository;
import com.cashify.service.CoinInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CoinInfoServiceImpl implements CoinInfoService {

	private static final Logger logger = LogManager.getLogger(CoinInfoServiceImpl.class);

	@Autowired
	CryptoCurrencyRepository cryptoCurrencyRepository;

	@Value("${coinmarketcap.api.url}")
	private String apiUrl;

	@Value("${coinmarketcap.api.key}")
	private String apiKey;

	@Override
	public String getCoinData(String symbols) throws JsonMappingException, JsonProcessingException {
		logger.info("Inside CoinInfoServiceImpl :: getCoinData()");

		RestTemplate restTemplate = new RestTemplate();
		String url = apiUrl + "?symbol=" + symbols;
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-CMC_PRO_API_KEY", apiKey);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String responseBody = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(responseBody).path("data");

		Iterator<String> fieldNames = root.fieldNames();
		while (fieldNames.hasNext()) {
			String fieldName = fieldNames.next();
			JsonNode node = root.path(fieldName);

			CryptoCurrency cryptocurrency = new CryptoCurrency();
			cryptocurrency.setId(node.path("id").asLong());
			cryptocurrency.setName(node.path("name").asText());
			cryptocurrency.setSymbol(node.path("symbol").asText());
			cryptocurrency.setSlug(node.path("slug").asText());
			cryptocurrency.setNumMarketPairs(node.path("num_market_pairs").asInt());
			cryptocurrency.setDateAdded(node.path("date_added").asText());

			List<String> tags = new ArrayList<>();
			node.path("tags").forEach(tagNode -> tags.add(tagNode.asText()));
			cryptocurrency.setTags(tags);

			cryptocurrency.setMaxSupply(node.path("max_supply").isNull() ? null : node.path("max_supply").asLong());
			cryptocurrency.setCirculatingSupply(node.path("circulating_supply").asDouble());
			cryptocurrency.setTotalSupply(node.path("total_supply").asDouble());
			cryptocurrency.setIsActive(node.path("is_active").asInt());
			cryptocurrency.setInfiniteSupply(node.path("infinite_supply").asBoolean());
			cryptocurrency.setPlatform(node.path("platform").isNull() ? null : node.path("platform").asText());
			cryptocurrency.setCmcRank(node.path("cmc_rank").asInt());
			cryptocurrency.setIsFiat(node.path("is_fiat").asInt());
			cryptocurrency.setLastUpdated(node.path("last_updated").asText());
			cryptocurrency.setTvl(node.path("tvl").isNull() ? null : node.path("tvl").asDouble());

			JsonNode quoteNode = node.path("quote").path("USD");
			Quote quote = new Quote();
			quote.setPrice(quoteNode.path("price").isNull() ? null : quoteNode.path("price").asDouble());
			quote.setVolume24h(quoteNode.path("volume_24h").isNull() ? null : quoteNode.path("volume_24h").asDouble());
			quote.setVolumeChange24h(quoteNode.path("volume_change_24h").isNull() ? null
					: quoteNode.path("volume_change_24h").asDouble());
			quote.setPercentChange1h(quoteNode.path("percent_change_1h").isNull() ? null
					: quoteNode.path("percent_change_1h").asDouble());
			quote.setPercentChange24h(quoteNode.path("percent_change_24h").isNull() ? null
					: quoteNode.path("percent_change_24h").asDouble());
			quote.setPercentChange7d(quoteNode.path("percent_change_7d").isNull() ? null
					: quoteNode.path("percent_change_7d").asDouble());
			quote.setPercentChange30d(quoteNode.path("percent_change_30d").isNull() ? null
					: quoteNode.path("percent_change_30d").asDouble());
			quote.setPercentChange60d(quoteNode.path("percent_change_60d").isNull() ? null
					: quoteNode.path("percent_change_60d").asDouble());
			quote.setPercentChange90d(quoteNode.path("percent_change_90d").isNull() ? null
					: quoteNode.path("percent_change_90d").asDouble());
			quote.setMarketCap(quoteNode.path("market_cap").isNull() ? null : quoteNode.path("market_cap").asDouble());
			quote.setMarketCapDominance(quoteNode.path("market_cap_dominance").isNull() ? null
					: quoteNode.path("market_cap_dominance").asDouble());
			quote.setFullyDilutedMarketCap(quoteNode.path("fully_diluted_market_cap").isNull() ? null
					: quoteNode.path("fully_diluted_market_cap").asDouble());
			quote.setLastUpdated(quoteNode.path("last_updated").asText());

			cryptocurrency.setQuote(quote);

			cryptoCurrencyRepository.save(cryptocurrency);
		}

		return "succesfully Added";
	}
}
