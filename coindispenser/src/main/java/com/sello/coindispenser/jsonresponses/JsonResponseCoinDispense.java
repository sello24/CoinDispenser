package com.sello.coindispenser.jsonresponses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponseCoinDispense {

	@JsonProperty("dispensedCoins")
	private List<JsonResponseCoin> dispenseCoins  = new ArrayList<>();

	public List<JsonResponseCoin> getCoins() {
		return dispenseCoins;
	}

	public void addCoin(JsonResponseCoin dispenseCoin) {
		this.dispenseCoins.add(dispenseCoin);
	}
	
	public void addCoins(List<JsonResponseCoin> dispenseCoins) {
		if(dispenseCoins != null && !dispenseCoins.isEmpty()) {
			this.dispenseCoins.addAll(dispenseCoins);
		}
	}

	@Override
	public String toString() {
		return "DispenseResponseJson [coins=" + dispenseCoins + "]";
	}
	
}
