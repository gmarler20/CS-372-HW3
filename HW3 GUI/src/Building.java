/**
 * This abstract class models a building.
 * @author Griffen Marler
 * @version 1.00, 9 Jan 2019
 */

import java.util.ArrayList;
public abstract class Building {
	private String name;
	private String address;

	/**
	 * Initialize the building given nothing
	 */
	public Building() {
		name = "Default";
		address = "300 W Hawthorne";
	}

	/**
	 * Initialize the building given name and an address
	 * @param n specifies name
	 * @param a specifies address
	 */
	public Building (String n, String a) {
		name = n;
		address = a;
	}

	/**
	 * Sets the name of the building
	 * @param n name
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Gets the name of the building
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the address
	 * @param a address
	 */
	public void setAddress(String a) {
		address = a;
	}

	/**
	 * Gets the name of the address
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Abstract function that will be used by both City Hall and School
	 * to add a person to that specific building
	 * @param p Person object that will go into the building
	 */
	abstract void add(Person p);

	/**
	 * Abstract function that will be used by both City Hall and School
	 * to list the names of the people in those buildings
	 */
	abstract void ListNames();

	/**
	 * Abstract function that will be used by both City Hall and School
	 * to list the specific types of people in each building
	 */
	abstract void ListSpecificTypes();

	abstract void PayEmployeeSalary(double a);
}

