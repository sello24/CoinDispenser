package com.sello.coindispenser.jsonresponses.jsonmappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sello.coindispenser.jsonresponses.JsonResponseCoin;
import com.sello.coindispenser.objects.CoinDispenserBusinessObject;


@Component
public class JsonRsponseMapper {
	
	public List<JsonResponseCoin> map(List<CoinDispenserBusinessObject> coinObjList) {
		List<JsonResponseCoin> coinResponseJsonList = null;
		if (coinObjList != null && !coinObjList.isEmpty()) {
			
			 coinResponseJsonList =  coinObjList.stream()
			.map(coinBusinessObj -> new JsonResponseCoin(coinBusinessObj.getCoinType(), coinBusinessObj.getNumberOfCoins())).collect(Collectors.toList());
		
		}
		
		return coinResponseJsonList;
	}

}
