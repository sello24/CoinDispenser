package com.sello.coindispenser.service;

import java.util.List;

import com.sello.coindispenser.objects.CoinDispenserBusinessObject;


public interface CoinDispenserService {
	
	public List<CoinDispenserBusinessObject> dispenseCoins(int amtRequested, boolean isAscOrderSort);

}
