/**
 * This class models a kid and extends the person class
 * @author Griffen Marler
 * @version 1.00, Jan 9, 2019
 */

import java.util.Random;
public class Kid extends Person {
		private String favcandy;

	/**
	 * Initialize the Kid given nothing
	 */
	public Kid() {
		Random rand = new Random();
		int num = rand.nextInt(3);

		if(num == 0) {
			favcandy = "Kit Kat";
		}
		else if (num == 1)
		{
			favcandy = "Twix";
		}
		else {
			favcandy = "Skittles";
		}
		}


	/**
	 * Initialize the kid given name, age, phone number, favorite candy
	 * @param n specifies name
	 * @param a specifies age
	 * @param f specifies favorite candy
	 */
		public Kid(String n, int a, String f) {

				this.SetName(n);
				this.SetAge(a);
				favcandy = f;

		}

	/**
	 * Sets the kids favorite candy
	 * @param f favorite candy
	 */
	public void SetFavcandy(String f)
		{
			favcandy = f;
		}

	/**
	 * Gets the kids favorite candy
	 * @return favorite candy
	 */
		public String GetFavcandy() {
			return favcandy;
		}

	/**
	 * Convert the object to a string
	 * @return vital information about the object
	 */
		public String toString() {
			return String.format("%s, a kid with the favorite candy of %s, the age of %d, and phone number of %d", this.getName(), this.GetFavcandy(), this.GetAge(), this.GetNumber());
		}
}
