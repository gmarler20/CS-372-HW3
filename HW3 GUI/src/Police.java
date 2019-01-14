/**
 * This class models a Police officer and extends the Person class as well as
 * implements employee.
 * @author Griffen Marler
 * @version 1.00, Jan 9, 2019
 */
import java.util.Random;
public class Police extends Person implements Employee {
	public enum Role {Patrol, Sargent, Captain, Chief};
	private Role R;

	/**
	 * Initialize the police object given nothing
	 */
	public Police() {
		Random rand = new Random();
		int num = rand.nextInt(4);

		if(num == 0) {
			R = Role.Patrol;
		}
		else if(num == 1) {
			R = Role.Sargent;
		}
		else if(num == 2) {
			R = Role.Captain;
		}
		else {
			R = Role.Chief;
		}
	}


	/**
	 * Initialize the police object given name, age, phone number, and role
	 * @param n specifies name
	 * @param a specifies age
	 * @param pn specifies phone number
	 * @param r specifies role
	 */
	public Police (String n, int a, Role r) {
		this.SetName(n);
		this.SetAge(a);
		R = r;
	}

	/**
	 * Return the role of the officer
	 * @return role the oficer has
	 */
	public Role getRole() {
		return R;
	}

	/**
	 * Return the employee ID of the officer
	 * @return Employee ID values
	 */
	public int AskId() {
		return this.getID();
	}

	/**
	 * Add to the employee's cash, allow them to be paid
	 * @param a amount of cash that will be added
	 */
	public void PayEmployee(double a) {
		this.setCash(this.getCash() + a);
	}

	/**
	 * Convert the object to a string
	 * @return vital information about the object
	 */
	public String toString() {
		return String.format("%s, a police officer in the role of %s with the age of %d, phone number %d, ID number %d, and cash of %f " , this.getName(), this.getRole().toString(), this.GetAge(), this.GetNumber(), this.getID(), this.getCash());

	}
	
}
