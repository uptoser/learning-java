package com.uptoser.concurrency.chapter_05_fork_join.chapter_05_01_fork_pool_recursive_action;

/**
 * This class stores the data of a Product. It's name and it's price
 *
 */
public class Product {

	/**
	 * Name of the product
	 */
	private String name;

	/**
	 * Price of the product
	 */
	private double price;

	/**
	 * This method returns the name of the product
	 * 
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method establish the name of the product
	 * 
	 * @param name
	 *            the name of the product
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the price of the product
	 * 
	 * @return the price of the product
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * This method establish the price of the product
	 * 
	 * @param price
	 *            the price of the product
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
