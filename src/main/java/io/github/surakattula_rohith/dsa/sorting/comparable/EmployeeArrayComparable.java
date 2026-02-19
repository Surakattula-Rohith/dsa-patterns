package io.github.surakattula_rohith.dsa.sorting.comparable;

import java.util.Arrays;

public class EmployeeArrayComparable {

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

            // 1️⃣ Age ascending
            if (this.age > other.age) return 1;
            if (this.age < other.age) return -1;

            // 2️⃣ If age same → salary descending
            if (this.salary < other.salary) return 1;
            if (this.salary > other.salary) return -1;

            return 0;
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

        Arrays.sort(employees);

        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}

