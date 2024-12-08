package Lab8;

import java.util.*;

public class Lab8 {

    public static class Worker {
        private String firstName;
        private String lastName;
        private double salary;
        private String position;

        public Worker(String firstName, String lastName, double salary, String position) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
            this.position = position;
        }

        public double getSalary() {
            return salary;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return "Worker{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", salary=" + salary +
                    ", position='" + position + '\'' +
                    '}';
        }
    }

    public static class Department {
        private String name;
        private Worker headOfDepartment;
        private Set<Worker> workers;

        public Department(String name, Worker headOfDepartment) {
            this.name = name;
            this.headOfDepartment = headOfDepartment;
            this.workers = new HashSet<>();
        }

        public void addWorker(Worker worker) {
            workers.add(worker);
        }

        public Set<Worker> getWorkers() {
            return workers;
        }

        public Worker getHeadOfDepartment() {
            return headOfDepartment;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "name='" + name + '\'' +
                    ", headOfDepartment=" + headOfDepartment +
                    ", workers=" + workers +
                    '}';
        }
    }

    public static class Company {
        private String name;
        private Worker director;
        private List<Department> departments;
        private Map<String, Double> employeeSalaries;

        public Company(String name, Worker director) {
            this.name = name;
            this.director = director;
            this.departments = new ArrayList<>();
            this.employeeSalaries = new HashMap<>();
        }

        public void addDepartment(Department department) {
            departments.add(department);
            for (Worker worker : department.getWorkers()) {
                employeeSalaries.put(worker.getFirstName() + " " + worker.getLastName(), worker.getSalary());
            }
            employeeSalaries.put(department.getHeadOfDepartment().getFirstName() + " " +
                    department.getHeadOfDepartment().getLastName(), department.getHeadOfDepartment().getSalary());
        }

        public List<Department> getDepartments() {
            return departments;
        }

        public double findMaxSalary() {
            return Collections.max(employeeSalaries.values());
        }

        public List<String> findWorkersHigherThanBossSalary() {
            List<String> result = new ArrayList<>();
            for (Department department : departments) {
                for (Worker worker : department.getWorkers()) {
                    if (worker.getSalary() > department.getHeadOfDepartment().getSalary()) {
                        result.add(worker.getFirstName() + " " + worker.getLastName());
                    }
                }
            }
            return result;
        }

        public List<String> getAllWorkers() {
            List<String> allWorkers = new ArrayList<>(employeeSalaries.keySet());
            allWorkers.add(director.getFirstName() + " " + director.getLastName());
            return allWorkers;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", director=" + director +
                    ", departments=" + departments +
                    '}';
        }

        public static void main(String[] args) {
            Worker director = new Worker("Jack", "Sparrow", 100000, "Director");
            Company company = new Company("Example Company", director);

            Department department1 = new Department("IT", new Worker("Dominic", "Toretto", 80000, "Head"));
            department1.addWorker(new Worker("John", "Doe", 50000, "Employee"));
            department1.addWorker(new Worker("Alice", "Smith", 90000, "Employee"));

            Department department2 = new Department("Finance", new Worker("Obi-wan", "Kenobi", 90000, "Head"));
            department2.addWorker(new Worker("Bob", "Johnson", 55000, "Employee"));
            department2.addWorker(new Worker("Eva", "Williams", 70000, "Employee"));

            company.addDepartment(department1);
            company.addDepartment(department2);

            double maxSalary = company.findMaxSalary();
            System.out.println("Максимальна зарплата: " + maxSalary);

            List<String> workersHigherSalaryThanBoss = company.findWorkersHigherThanBossSalary();
            System.out.println("Працівники з більшою зарплатою за свого начальника: " + workersHigherSalaryThanBoss);

            List<String> allWorkers = company.getAllWorkers();
            System.out.println("Список усіх працівників компанії: " + allWorkers);
        }
    }

    public static void main(String[] args) {
        Company.main(args);
    }
}
