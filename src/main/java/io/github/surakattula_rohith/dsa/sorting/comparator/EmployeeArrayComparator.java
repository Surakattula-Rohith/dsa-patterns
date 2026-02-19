package io.github.surakattula_rohith.dsa.sorting.comparator;

import java.util.Arrays;
import java.util.Comparator;

public class EmployeeArrayComparator {

    static class Employee {
        String name;
        int age;
        double salary;

        Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return name + " | " + age + " | " + salary;
        }
    }

    public static void main(String[] args) {

        Employee[] employees = {
                new Employee("Rohith", 25, 50000),
                new Employee("Amit", 30, 70000),
                new Employee("John", 25, 60000)
        };

        Arrays.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {

                // 1️⃣ Compare age (ascending)
                if (e1.age > e2.age) return 1;     // e1 comes after e2
                if (e1.age < e2.age) return -1;    // e1 comes before e2

                // 2️⃣ If age same → compare salary (descending)
                if (e1.salary < e2.salary) return 1;
                if (e1.salary > e2.salary) return -1;

                return 0; // completely equal
            }
        });

        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}

