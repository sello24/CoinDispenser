package com.sello.coindispenser.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.sello.coindispenser.handleexceptions.ChangeUnavailableException;
import com.sello.coindispenser.handleexceptions.DatabaseError;
import com.sello.coindispenser.handleexceptions.ErrorCode;
import com.sello.coindispenser.handleexceptions.ErrorType;
import com.sello.coindispenser.objects.CoinDispenserBusinessObject;
import com.sello.coindispenser.repository.dto.CoinDispenserDTO;
import com.sello.coindispenser.repositoryy.CoinDispenserRepository;

/* This class gets all the available coin type with remaining quantities from the db
 * Given an amount in cash that can be (1, 2, 5, 10, 20, 50, 100)
 * 
 * 
 */

public class CoinDispenserServiceImpl implements CoinDispenserService{
	
	
	@Autowired
	private CoinDispenserRepository coinDispenserRepository;
	
	
	@Override
	public List<CoinDispenserBusinessObject> dispenseCoins(int amtRequested, boolean isAscOrderSort) {
		
		try {
			
			List<CoinDispenserDTO> availableCoinList = coinDispenserRepository.getAvailableCoinsList(isAscOrderSort);

			if(availableCoinList == null || availableCoinList.isEmpty()) {
				throw new ChangeUnavailableException(ErrorCode.COINDISPENSE_003_BUSINESS_LOGIC, ErrorType.BUSINESS_LOGIC, "The requested amount does not have change");
			}
			
			float retrievedTotalAmountFromDb = 0;

			Map<Float, Integer> processedCoinsMap = new HashMap<>();

			if (availableCoinList != null && !availableCoinList.isEmpty()) {
				
							
				for (CoinDispenserDTO coinDispenserDTO : availableCoinList) {

					float type = coinDispenserDTO.getType();
					int remainingDBCoins = coinDispenserDTO.getQuantityLeft();
					
					int requiredNumberOfCoinsForType = (int) ((amtRequested - retrievedTotalAmountFromDb) / type);
					
					if (requiredNumberOfCoinsForType <= remainingDBCoins) {
						retrievedTotalAmountFromDb += type * requiredNumberOfCoinsForType;
						processedCoinsMap.put(type, requiredNumberOfCoinsForType);
					} else {
						
						retrievedTotalAmountFromDb += type * remainingDBCoins;
						processedCoinsMap.put(type, remainingDBCoins);
					}

					if (retrievedTotalAmountFromDb >= amtRequested) {
						break;
					}
					
				}

				if (retrievedTotalAmountFromDb != amtRequested) {
					throw new ChangeUnavailableException(ErrorCode.COINDISPENSE_003_BUSINESS_LOGIC, ErrorType.BUSINESS_LOGIC, "The requested amount does not have change");
				}
				coinDispenserRepository.updateDispensedQtyWithProcessedQty(processedCoinsMap);
			}
			
			List<CoinDispenserBusinessObject> coinDispenserList =  createCoinDispenserBusinessObjectsList(processedCoinsMap);
			
			return coinDispenserList;
		} catch (DatabaseError dbErr) {
			throw new DatabaseError(HttpStatus.INTERNAL_SERVER_ERROR,ErrorCode.COINDISPENSE_001_DB, ErrorType.APPLICATION_ERROR, "ApplicationError");
		}

	}

	private List<CoinDispenserBusinessObject> createCoinDispenserBusinessObjectsList(Map<Float, Integer> processedCoinsMap) {
		List<CoinDispenserBusinessObject> boList = null;
		if (processedCoinsMap != null && !processedCoinsMap.isEmpty()) {

			boList = new ArrayList<>();
			for (Map.Entry<Float, Integer> entry : processedCoinsMap.entrySet()) {
				CoinDispenserBusinessObject coinBO = new CoinDispenserBusinessObject();
				coinBO.setCoinType(entry.getKey());
				coinBO.setNumberOfCoins(entry.getValue());

				boList.add(coinBO);
			}
		}
		return boList;
	}


}
