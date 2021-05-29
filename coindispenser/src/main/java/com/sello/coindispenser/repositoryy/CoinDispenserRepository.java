package com.sello.coindispenser.repositoryy;

import java.util.List;
import java.util.Map;

import com.sello.coindispenser.repository.dto.CoinDispenserDTO;


public interface CoinDispenserRepository {

	public void updateDispensedQtyWithProcessedQty(Map<Float, Integer> processedCoinsQtyMap);
	public List<CoinDispenserDTO> getAvailableCoinsList(boolean isMinNumberOfCoins);
	
}
