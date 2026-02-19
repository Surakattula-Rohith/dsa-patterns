package io.github.surakattula_rohith.dsa.sorting.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeListComparable {

    static class Employee implements Comparable<Employee> {

        String name;
        int age;
        double salary;

        Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        @Override
        public int compareTo(Employee other) {

            // Natural ordering by age ascending
            if (this.age > other.age) return 1;
            if (this.age < other.age) return -1;

            return 0;
        }

        @Override
        public String toString() {
            return name + " | " + age + " | " + salary;
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Rohith", 25, 50000));
        employees.add(new Employee("Amit", 30, 70000));
        employees.add(new Employee("John", 25, 60000));

        Collections.sort(employees);

        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}

