/**
 * This interface contains methods to give employee's their pay and a
 * method to ask the employee their EmployeeID. Police and Teacher
 * classes will implement Employee.
 * @author Griffen Marler
 * @version 1.00, 9 Jan 2019
 */
public interface Employee {
    int AskId();
    void PayEmployee(double a);
}
