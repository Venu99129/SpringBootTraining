package org.example.streams;

import java.util.ArrayList;
import java.util.List;

public class StreamMapPractice {

    static List<UserDTO> dtoList = new ArrayList<>();

    static {
        dtoList.add(new UserDTO(1, "Arjun Mehta", 45000.00, 25));
        dtoList.add(new UserDTO(2, "Priya Sharma", 52000.00, 28));
        dtoList.add(new UserDTO(3, "Rohan Verma", 60000.00, 32));
        dtoList.add(new UserDTO(4, "Sneha Kulkarni", 48000.00, 26));
        dtoList.add(new UserDTO(5, "Karan Patel", 75000.00, 35));
        dtoList.add(new UserDTO(6, "Aditi Desai", 68000.00, 30));
        dtoList.add(new UserDTO(7, "Vikas Kumar", 42000.00, 24));
        dtoList.add(new UserDTO(8, "Neha Singh", 55000.00, 29));
        dtoList.add(new UserDTO(9, "Rajeev Ranjan", 80000.00, 38));
        dtoList.add(new UserDTO(10, "Shweta Nair", 47000.00, 27));

        dtoList.add(new UserDTO(11, "Harshit Gupta", 52000.00, 28));
        dtoList.add(new UserDTO(12, "Meera Joshi", 61000.00, 31));
        dtoList.add(new UserDTO(13, "Aman Tiwari", 53000.00, 26));
        dtoList.add(new UserDTO(14, "Ritika Choudhary", 70000.00, 34));
        dtoList.add(new UserDTO(15, "Suresh Yadav", 39000.00, 23));
        dtoList.add(new UserDTO(16, "Pooja Bhat", 69000.00, 33));
        dtoList.add(new UserDTO(17, "Aditya Rao", 75000.00, 36));
        dtoList.add(new UserDTO(18, "Nisha Jain", 58000.00, 28));
        dtoList.add(new UserDTO(19, "Raghav Kapoor", 82000.00, 40));
        dtoList.add(new UserDTO(20, "Divya Menon", 46000.00, 25));
    }


    public static void main(String[] args) {
        //mapping dto to users class using streams
        dtoList.stream().map(dto -> new User(dto.getId(), dto.getName(), dto.getSalary(), dto.getAge())).forEach(System.out::println);
    }
}

class User{
    int id;
    String name;
    double salary;
    int age;

    public User(int id, String name, double salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}


class UserDTO{
    int id;
    String name;
    double salary;
    int age;

    public UserDTO(int id, String name, double salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "userDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
