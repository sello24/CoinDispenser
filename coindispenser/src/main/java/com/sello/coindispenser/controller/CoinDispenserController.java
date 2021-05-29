package com.sello.coindispenser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sello.coindispenser.controller.validator.CoinDispenseValidator;
import com.sello.coindispenser.jsonresponses.JsonResponseCoin;
import com.sello.coindispenser.jsonresponses.JsonResponseCoinDispense;
import com.sello.coindispenser.jsonresponses.jsonmappers.JsonRsponseMapper;
import com.sello.coindispenser.objects.CoinDispenserBusinessObject;
import com.sello.coindispenser.service.CoinDispenserService;

/*
 * Exposing the service
 */

@RequestMapping("/dispensecoin")
public class CoinDispenserController{

	@Autowired
	private CoinDispenserService coinDispenserService;
	
	@Autowired
	private JsonRsponseMapper responseJsonMapper;
	
	
	@GetMapping("{amount}")
	public ResponseEntity<JsonResponseCoinDispense> dispenseCoin(@PathVariable("amount") int amount,
	@RequestParam(value = "leastNumberOfCoins", defaultValue = "true") String leastNumberOfCoins) {
		
		//Validate the Input
		CoinDispenseValidator.validateDispenseRequest(amount, leastNumberOfCoins);

			List<CoinDispenserBusinessObject> coinObjList = coinDispenserService.dispenseCoins(amount, Boolean.parseBoolean(leastNumberOfCoins));
			List<JsonResponseCoin> coinResponseJsonList = responseJsonMapper.map(coinObjList);
			JsonResponseCoinDispense dispenseResponseJson =  new JsonResponseCoinDispense();
			dispenseResponseJson.addCoins(coinResponseJsonList);
		
			return new ResponseEntity<JsonResponseCoinDispense>(dispenseResponseJson, HttpStatus.OK);

	}

}
