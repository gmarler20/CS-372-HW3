/**
 * This class models a School building, extending the building class.
 * @author Griffen Marler
 * @version 1.00, Jan 9 2019
 */

import org.omg.CORBA.Current;

import java.util.ArrayList;

public class School extends Building {
    private ArrayList<Person> CurrentOccupants = new ArrayList<Person>();

    /**
     * Initializes a school object given nothing
     */
    public School() {
        this.setName("School");
        this.setAddress("300 W Hawthorne");
    }

    public School(String a, String b) {
        this.setName(a);
        this.setAddress(b);
    }

    /**
     * Adds a person to the CurrentOccupants ArrayList
     * @param p Person object that will go into the building
     */
    public void add(Person p) {
        CurrentOccupants.add(p);
    }

    /**
     * Outputs all the people in the schools
     */
    public void ListNames() {
        for(int i = 0; i < CurrentOccupants.size(); i++)
        {
            System.out.printf("%s is in the school\n", CurrentOccupants.get(i).toString());
        }
    }

    /**
     * Outputs all the kids and teachers in school
     */
    public void ListSpecificTypes() {
        System.out.println("The kids currently in school are ");
        for(int i = 0; i < CurrentOccupants.size(); i++) {
            if(CurrentOccupants.get(i) instanceof Kid) {
                System.out.println(CurrentOccupants.get(i).getName());
            }
        }
        System.out.println("The teachers currently in school are ");
        for(int i = 0; i < CurrentOccupants.size(); i++) {
            if(CurrentOccupants.get(i) instanceof Teacher) {
                System.out.println(CurrentOccupants.get(i).getName());
            }
        }
    }

    /**
     * Allows the city to pay out a salary to its employees
     * @param a Amount being paid out
     */
    public void PayEmployeeSalary(double a) {
        for(int i = 0; i < CurrentOccupants.size(); i++) {
            if(CurrentOccupants.get(i) instanceof Employee) {
                CurrentOccupants.get(i).setCash(a);
            }
        }
    }

}
