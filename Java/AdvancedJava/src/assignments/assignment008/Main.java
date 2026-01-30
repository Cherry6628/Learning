package assignments.assignment008;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

record Employee(int id, String name) {
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee e)) return false;
        return this.id == e.id;
    }

    public int hashCode() {
        return Integer.hashCode(id);
    }
}

class EmployeeProjectManagementSystem {
    private HashMap<Employee, List<String>> projects = new HashMap<>();
    private int employeeCount = 0;

    boolean addNewEmployee(Employee e) {
        if (e == null) return false;
        projects.putIfAbsent(e, new ArrayList<>());
        return true;
    }

    boolean addNewEmployee(String name) {
        if (name == null || name.isBlank()) return false;
        Employee e = new Employee(employeeCount++, name.strip());
        projects.put(e, new ArrayList<>());
        return true;
    }

    boolean assignProject(Employee e, String projectDescription) {
        if (e == null || projectDescription == null || projectDescription.isBlank()) return false;

        List<String> list = projects.get(e);
        if (list == null) return false;

        return list.add(projectDescription.strip());
    }

    boolean removeProject(Employee e, String project) {
        if (e == null || project == null) return false;

        List<String> list = projects.get(e);
        if (list == null) return false;

        return list.remove(project);
    }

    void displayAll() {
        projects.forEach((emp, list) -> 
            System.out.println(emp.id() + " - " + emp.name() + " : " + list)
        );
    }
}

public class Main {
	public static void main(String[] args) {
        EmployeeProjectManagementSystem sys = new EmployeeProjectManagementSystem();

        sys.addNewEmployee("Sanjeevi");
        sys.addNewEmployee("Sanjeevi 2");

        Employee e1 = new Employee(0, "Sanjeevi");
        Employee e2 = new Employee(1, "Sanjeevi 2");

        sys.assignProject(e1, "Database");
        sys.assignProject(e1, "Security");
        sys.assignProject(e2, "Frontend");


        sys.displayAll();
        
        System.out.println("");
        sys.removeProject(e1, "Database");

        sys.displayAll();
    }
}
