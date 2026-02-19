package io.github.surakattula_rohith.dsa.sorting.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeListComparator {

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

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Rohith", 25, 50000));
        employees.add(new Employee("Amit", 30, 70000));
        employees.add(new Employee("John", 25, 60000));

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {

                // 1️⃣ Salary descending
                if (e1.salary < e2.salary) return 1;
                if (e1.salary > e2.salary) return -1;

                // 2️⃣ If salary same → name ascending
                if (e1.name.compareTo(e2.name) > 0) return 1;
                if (e1.name.compareTo(e2.name) < 0) return -1;

                return 0;
            }
        });

        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}

