/**
 * This class models a city hall type building and extends Building
 * @author Griffen Marler
 * @version 1.00, Jan 9 2019
 */

import java.util.ArrayList;

public class CityHall extends Building {
    private ArrayList<Person> CurrentOccupants = new ArrayList<Person>();

    /**
     * Initializes the city hall given nothing
     */
    public CityHall() {
        this.setName("City Hall");
        this.setAddress("400 W Main");
    }

    public CityHall(String a, String b) {
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
     * Outputs the names of everyone currently in the city hall
     */
    public void ListNames() {
        for(int i = 0; i < CurrentOccupants.size(); i++) {
            System.out.printf("%s is in the city hall\n", CurrentOccupants.get(i).toString());
        }
    }

    /**
     * Outputs the names of the police officers in city hall
     */
    public void ListSpecificTypes() {
        System.out.println("The police officers currently in city hall are: ");
        for(int i = 0; i < CurrentOccupants.size(); i++) {
            if(CurrentOccupants.get(i) instanceof Police) {
                System.out.println(CurrentOccupants.get(i).getName());
            }
        }
    }

    /**
     * Allows the City to pay out a  salary to its employees
     * @param a amount of money that employee is being paid
     */
    public void PayEmployeeSalary(double a) {
        for(int i = 0; i < CurrentOccupants.size(); i++) {
            if(CurrentOccupants.get(i) instanceof Employee) {
                CurrentOccupants.get(i).setCash(a);
            }
        }
    }

}
