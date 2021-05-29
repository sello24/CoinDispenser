package com.sello.coindispenser.repository.dto;

public class CoinDispenserDTO {
	
	
private int id;
	
	private float type;
	
	private int quantityLeft;

	public int getQuantityLeft() {
		return quantityLeft;
	}

	public void setQuantityLeft(int quantityLeft) {
		this.quantityLeft = quantityLeft;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getType() {
		return type;
	}

	public void setType(float type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "CoinDispenserDTO [id=" + id + ", type=" + type + ", quantityLeft=" + quantityLeft + "]";
	}
	

}
