package com.uptoser.java.concurrency.chapter_06_concurrent_data_structure.chapter_06_05_ConcurrentSkipListMap;

/**
 * This class implements a Contact to store in the navigable map
 *
 */
public class Contact {

	/**
	 * Name of the contact
	 */
	private String name;

	/**
	 * Phone number of the contact
	 */
	private String phone;

	/**
	 * Constructor of the class
	 * 
	 * @param name
	 *            Name of the contact
	 * @param phone
	 *            Phone number of the contact
	 */
	public Contact(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	/**
	 * Method that returns the name of the contact
	 * 
	 * @return The name of the contact
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method that returns the phone number of the contact
	 * 
	 * @return
	 */
	public String getPhone() {
		return phone;
	}
}
