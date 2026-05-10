package app;

import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DatabaseConnector db = new DatabaseConnector();


        db.connect();

        Connection connection = db.getConnection();



        EmployeeDAO employeeDAO = new EmployeeDAO(connection);


        Employee emp1 = new Employee("John", 25, "Developer", 10000);
        employeeDAO.addEmployee(emp1);

        Employee emp2 = new Employee("Robert", 30, "Manager", 8000);
        employeeDAO.addEmployee(emp2);


        System.out.println("\nList of employyes:");

        List<Employee> employees = employeeDAO.getAllEmployees();

        for (Employee emp : employees) {
            System.out.println(emp);
        }


        Employee updatedEmployee = new Employee(1, "Kventin", 26, "Senior Developer", 15000);
        employeeDAO.updateEmployee(updatedEmployee);


        employeeDAO.deleteEmployee(2);


        System.out.println("\nAfter updating and deleting:");

        employees = employeeDAO.getAllEmployees();

        for (Employee emp : employees) {
            System.out.println(emp);
        }


        db.disconnect();
    }
}