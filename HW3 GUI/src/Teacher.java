/**
 * This class models a Teacher and extends Person as well as implement Employee.
 * @author Griffen Marler
 * @version 1.00, Jan 9 2019
 */

import java.util.Random;
public class Teacher extends Person implements Employee {
	private int glevel;
	private String certif;

	/**
	 * Initialize the teacher given no paramets
	 */
	public Teacher() {
		Random rand = new Random();
		glevel = rand.nextInt(12) + 1;

		Random rand2 = new Random();
		int num = rand2.nextInt(4);

		if(num == 0) {
			certif = "Math";
		}
		if(num == 1) {
			certif = "English";
		}
		if(num == 2) {
			certif = "History";
		}
		if(num == 3) {
			certif = "Science";
		}
	}

	/**
	 * Initialize the teacher given name, age, phone number, grade level, certification
	 * @param n specifies name
	 * @param a specifies age
	 * @param pn specifies phone number
	 * @param g specifies grade
	 * @param c specifies certification
	 */
	public Teacher(String n, int a,  int g, String c) {
		this.SetName(n);
		this.SetAge(a);
		glevel = g;
		certif = c;
	}

	/**
	 * Returns teacher's employee ID
	 * @return ID number
	 */
	public int AskId() {
		return this.getID();
	}

	/**
	 * Allows the teacher to be paid for their work
	 * @param a amount of cash to be added
	 */
	public void PayEmployee(double a) {
		this.setCash(this.getCash() + a);
	}

	/**
	 * Sets the grade level for the teacher
	 * @param g grade level to be set
	 */
	public void SetGlevel(int g) {
		glevel = g;
	}

	/**
	 * Returns the teachers grade level
	 * @return grade level
	 */
	int GetGlevel() {
		return glevel;
	}

	/**
	 * Set the teacher's cerrtification
	 * @param c type of certification to be set
	 */
	public void SetCertif(String c) {
		certif = c;
	}

	/**
	 * Return certification of teacher
	 * @return certification
	 */
	String getCertif() {
		return certif;
	}

	/**
	 * Convert the object to a string
	 * @return vital information about the object
	 */
	public String toString() {
		return String.format("%s, a Teacher with the grade level of %d, certification of %s, age of %d, phone number %d, ID number %d and cash of %f", this.getName(), this.GetGlevel(), this.getCertif(), this.GetAge(), this.GetNumber(), this.getID(), this.getCash());
	}
}
