package org.example.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroupingByAndPartitionByCLass {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("venu","venu@gmail.com","IT",31000.00));
        employees.add(new Employee("madhav","madhav@gmail.com","IT",75000.00));
        employees.add(new Employee("Aishu","aishu@gmail.com","Realestate",100000.00));
        employees.add(new Employee("gopal","gopal@gmail.com","IT",250000.00));

        //groupingBy method using for divide list group by department
        Map<String , List<Employee>> collectedMap = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("IT department    -> ");
        System.out.println(collectedMap.get("IT"));

        System.out.println("----------------------------------------------");

        System.out.println("Realestate department ->");
        System.out.println(collectedMap.get("Realestate"));

        System.out.println("----------------------------------------------");
        //partitionBy method divide list of partition based on salary >50000 returns boolean ket , value is list

        Map<Boolean , List<Employee>> partitionMap = employees.stream().collect(Collectors.partitioningBy(emp -> emp.getSalary()>50000));

        System.out.println("employee salary garter than 50000");
        System.out.println(partitionMap.get(true));

        System.out.println("employee salary less than 50000");
        System.out.println(partitionMap.get(false));

    }
}

class Employee{
    String name;
    String email;
    String department;
    Double salary;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Employee(String name, String email, String department, Double salary) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
