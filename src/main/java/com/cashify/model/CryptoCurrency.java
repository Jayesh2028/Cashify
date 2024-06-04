package com.cashify.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_currency")
public class CryptoCurrency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String symbol;
	private String slug;
	private int numMarketPairs;
	private String dateAdded;

	@ElementCollection
	private List<String> tags;

	private Long maxSupply;
	private Double circulatingSupply;
	private Double totalSupply;
	private int isActive;
	private boolean infiniteSupply;
	private String platform;
	private int cmcRank;
	private int isFiat;
	private String lastUpdated;
	private Double tvl;

	@OneToOne(cascade = CascadeType.ALL)
	private Quote quote;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public int getNumMarketPairs() {
		return numMarketPairs;
	}

	public void setNumMarketPairs(int numMarketPairs) {
		this.numMarketPairs = numMarketPairs;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Long getMaxSupply() {
		return maxSupply;
	}

	public void setMaxSupply(Long maxSupply) {
		this.maxSupply = maxSupply;
	}

	public Double getCirculatingSupply() {
		return circulatingSupply;
	}

	public void setCirculatingSupply(Double circulatingSupply) {
		this.circulatingSupply = circulatingSupply;
	}

	public Double getTotalSupply() {
		return totalSupply;
	}

	public void setTotalSupply(Double totalSupply) {
		this.totalSupply = totalSupply;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public boolean isInfiniteSupply() {
		return infiniteSupply;
	}

	public void setInfiniteSupply(boolean infiniteSupply) {
		this.infiniteSupply = infiniteSupply;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getCmcRank() {
		return cmcRank;
	}

	public void setCmcRank(int cmcRank) {
		this.cmcRank = cmcRank;
	}

	public int getIsFiat() {
		return isFiat;
	}

	public void setIsFiat(int isFiat) {
		this.isFiat = isFiat;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Double getTvl() {
		return tvl;
	}

	public void setTvl(Double tvl) {
		this.tvl = tvl;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

}
