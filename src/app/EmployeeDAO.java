package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }


    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO public.employees(name, age, position, salary) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getPosition());
            pstmt.setFloat(4, employee.getSalary());

            pstmt.executeUpdate();
            System.out.println("Employee was added!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateEmployee(Employee employee) {
        String sql = "UPDATE public.employees SET name=?, age=?, position=?, salary=? WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getPosition());
            pstmt.setFloat(4, employee.getSalary());
            pstmt.setInt(5, employee.getId());

            pstmt.executeUpdate();
            System.out.println("Employee was updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteEmployee(int id) {
        String sql = "DELETE FROM public.employees WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            System.out.println("Employee was deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM public.employees";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("position"),
                        rs.getFloat("salary")
                );

                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
