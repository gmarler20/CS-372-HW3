import java.util.Random;

/**
 * This class models a person in a city
 * @author Griffen Marler
 * @version 1.0 Jan 9 2019
 *
 *
 *
 */
public class Person {
	private static int LASTNUMBER = 1111111;
	private static int EmployeeID = 00001;
	private static int PersonID = 1;
	private int number;
	private int ID;
	private double cash;
	private Random _rnd = new Random();
	private String name;
	private int age;

	/**
	 * Initialize the person object given no parameters
	 */
	public Person() {
		number = LASTNUMBER;
		LASTNUMBER++;
		name = "Person" + PersonID;
		PersonID++;
		if (this instanceof Employee)
		{
			ID = EmployeeID;
			EmployeeID++;
			cash = 0.0;
			Random rand = new Random();
			int num = rand.nextInt(22);
			age = 20 + num;
		}
		else {
			Random rand2 = new Random();
			age = rand2.nextInt(14) + 1;
		}
	}
	
	/*public Person(String n, int a, int num, int idnum, int cash) {
		n = name;
		a = age;
		num = number;
		if (this instanceof Employee)
		{
			ID = idnum;
			this.cash = cash;
		}
	}

	public Person(String n, int a) {
		n = name;
		a = age;
		number = LASTNUMBER;
		LASTNUMBER++;
		if(this instanceof Employee) {
			ID = EmployeeID;
			EmployeeID++;
			cash = 0;
		}
	*/

	/**
	 * Set the name of the person
	 * @param n specifies the name
	 */
	public void SetName(String n) {
		name = n;
	}

	/**
	 * Return the name of the person
	 * @return returns name of person
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the age of the person
	 * @param a specifies the age
	 */
	public void SetAge(int a) {
		age = a;
	}

	/**
	 * Return the age of the person
	 * @return returns age of person
	 */
	public int GetAge() {
		return age;
	}

	/**
	 * Sets the phone number of the person
	 * @param b specifies phone number
	 */
	public void SetNumber(int b) {
		number = b;
	}

	/**
	 * Gets the phone number of the person
	 * @return phone number
	 */
	public int GetNumber() {
		return number;
	}

	/**
	 * Gets the ID of the person
	 * @return Employee ID of person
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets Employee ID of person
	 * @param ID person's Employee ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Gets the amount of cash a person has
	 * @return cash person has
	 */
	public double getCash() {
		return cash;
	}

	/**
	 * Sets the amount of cash a person has
	 * @param cash specifies amount of cash
	 */
	public void setCash(double cash) {
		this.cash = cash;
	}
}
