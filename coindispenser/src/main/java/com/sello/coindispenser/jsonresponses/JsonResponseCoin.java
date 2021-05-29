package com.sello.coindispenser.jsonresponses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponseCoin {
	
	@JsonProperty("type")
	private float coinType;
	
	@JsonProperty("quantity")
	private int numberOfCoins;
	
	public JsonResponseCoin(float coinType, int numberOfCoins) {
		this.coinType = coinType;
		this.numberOfCoins = numberOfCoins;
	}

	public float getCoinType() {
		return coinType;
	}

	public void setCoinType(float coinType) {
		this.coinType = coinType;
	}

	public int getNumberOfCoins() {
		return numberOfCoins;
	}

	public void setNumberOfCoins(int numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
	}

	@Override
	public String toString() {
		return "CoinResponseJson [coinType=" + coinType + ", numberOfCoins=" + numberOfCoins + "]";
	}
	

}
