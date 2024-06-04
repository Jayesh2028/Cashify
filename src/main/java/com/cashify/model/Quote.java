package com.cashify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Quote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double price;
	private Double volume24h;
	private Double volumeChange24h;
	private Double percentChange1h;
	private Double percentChange24h;
	private Double percentChange7d;
	private Double percentChange30d;
	private Double percentChange60d;
	private Double percentChange90d;
	private Double marketCap;
	private Double marketCapDominance;
	private Double fullyDilutedMarketCap;
	private String lastUpdated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getVolume24h() {
		return volume24h;
	}

	public void setVolume24h(Double volume24h) {
		this.volume24h = volume24h;
	}

	public Double getVolumeChange24h() {
		return volumeChange24h;
	}

	public void setVolumeChange24h(Double volumeChange24h) {
		this.volumeChange24h = volumeChange24h;
	}

	public Double getPercentChange1h() {
		return percentChange1h;
	}

	public void setPercentChange1h(Double percentChange1h) {
		this.percentChange1h = percentChange1h;
	}

	public Double getPercentChange24h() {
		return percentChange24h;
	}

	public void setPercentChange24h(Double percentChange24h) {
		this.percentChange24h = percentChange24h;
	}

	public Double getPercentChange7d() {
		return percentChange7d;
	}

	public void setPercentChange7d(Double percentChange7d) {
		this.percentChange7d = percentChange7d;
	}

	public Double getPercentChange30d() {
		return percentChange30d;
	}

	public void setPercentChange30d(Double percentChange30d) {
		this.percentChange30d = percentChange30d;
	}

	public Double getPercentChange60d() {
		return percentChange60d;
	}

	public void setPercentChange60d(Double percentChange60d) {
		this.percentChange60d = percentChange60d;
	}

	public Double getPercentChange90d() {
		return percentChange90d;
	}

	public void setPercentChange90d(Double percentChange90d) {
		this.percentChange90d = percentChange90d;
	}

	public Double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

	public Double getMarketCapDominance() {
		return marketCapDominance;
	}

	public void setMarketCapDominance(Double marketCapDominance) {
		this.marketCapDominance = marketCapDominance;
	}

	public Double getFullyDilutedMarketCap() {
		return fullyDilutedMarketCap;
	}

	public void setFullyDilutedMarketCap(Double fullyDilutedMarketCap) {
		this.fullyDilutedMarketCap = fullyDilutedMarketCap;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
