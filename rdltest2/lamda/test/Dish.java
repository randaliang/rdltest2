package test;

import lombok.Data;

public class Dish {

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		this.fruit = fruit;
	}

	public boolean isMeat() {
		return isMeat;
	}

	public void setMeat(boolean isMeat) {
		this.isMeat = isMeat;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	private String fruit;
	
	private boolean isMeat;
	
	private int price;
	
	private Type type;
	
	public Dish(String fruit, boolean isMeat, int price, Type type) {
		this.fruit = fruit;
		this.isMeat = isMeat;
		this.price = price;
		this.type = type;
	}
	
	
}
