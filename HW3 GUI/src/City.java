/**
 * This class models a city with different buildings and people.
 * @author Griffen Marler
 * @version 1.00, Jan 9, 2019
 */

import java.util.ArrayList;
import java.util.Random;
public class City {

    private ArrayList<Building> Buildings = new ArrayList<Building>();
    private ArrayList<Person> CityPeople = new ArrayList <Person>();

    /**
     * Initializes the city object given nothing, creating a new City Hall
     * and school as well as adding people into different buildings
     */
    public City() {
         CityHall C = new CityHall();
         School S = new School();
         Buildings.add(C);
         Buildings.add(S);
        for (int i = 0; i < 200; i++) {                     // Create 200 people in the city
            Random rand = new Random();
            int num = rand.nextInt(3);

            if (num == 0) {
                Buildings.get(0).add(GeneratePerson());
            } else if (num == 1) {
                Buildings.get(1).add(GeneratePerson());
            } else if (num == 2) {
                CityPeople.add(GeneratePerson());
            }
        }
    }

    /**
     * Generates a person by specific type using random number generation
     * @return a new person
     */
    public Person GeneratePerson() {
        Random rand = new Random();
        int number = rand.nextInt(3);

        if (number == 0) {
            Person n = new Teacher();
            return n;
        } else if (number == 1) {
            Person n = new Police();
            return n;
        } else {
            Person n = new Kid();
            return n;
        }

    }

    /**
     * Outputs the names of everyone in the city
     */
    public void OutputNames() {
        for(int i = 0; i < CityPeople.size(); i++)
        {
            System.out.printf("%s is in the city\n", CityPeople.get(i).toString());
        }

        for(int i = 0; i < Buildings.size(); i++) {                             // EDITED THIS
            Buildings.get(i).ListNames();
            Buildings.get(i).ListNames();
        }

    }

    /**
     * Outputs all of the buildings in the city
     */
    public void OutputBuildings() {
        System.out.printf("The names of all the buildings in the city are: \n");
        for(int i = 0; i < Buildings.size(); i++) {
          System.out.println(Buildings.get(i).getName() + " ");
        }
    }

    /**
     * Outputs all of the police in the City Hall
     */
    public void OutputPolice() {
        Buildings.get(0).ListSpecificTypes();
    }

    /**
     * Outputs all of the kids and teachers in school
     */
    public void OutputKidsandTeachers() {
        Buildings.get(1).ListSpecificTypes();
    }

    public void PayEmployeeSalary(double a) {
        for(int i = 0; i < CityPeople.size(); i++) {
            if(CityPeople.get(i) instanceof Employee) {
                CityPeople.get(i).setCash(a);
            }
        }
        Buildings.get(0).PayEmployeeSalary(a);
        Buildings.get(1).PayEmployeeSalary(a);

    }
}
