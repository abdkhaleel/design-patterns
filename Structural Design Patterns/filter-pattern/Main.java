import java.util.*;
class Employee {
    private final String name;
    private final String department;
    private final String gender;
    private final int experience;

    public Employee (String name, String department, String gender, int experience) {
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.experience = experience;
    }

    public String getDepartment () {
        return this.department;
    }

    public String getGender () {
        return this.gender;
    }

    public int getExperience () {
        return this.experience;
    }

    @Override
    public String toString () {
        return name + " | " + department + " | " + gender + " | " + experience + " yrs";
    }
}

interface Criteria {
    List<Employee> meetCriteria(List<Employee> employees);
}

class ITCriteria implements Criteria {
    @Override
    public List<Employee> meetCriteria (List<Employee> employees) {
        List<Employee> result = new ArrayList<>();
        for (Employee e: employees) {
            if (e.getDepartment().equalsIgnoreCase("IT")) {
                result.add(e);
            }
        }
        return result;
    }
}

class FemaleCriteria implements Criteria {
    @Override
    public List<Employee> meetCriteria (List<Employee> employees) {
        List<Employee> result = new ArrayList<>();
        for (Employee e: employees) {
            if (e.getGender().equalsIgnoreCase("Female")) {
                result.add(e);
            }
        }
        return result;
    }
}

class SeniorCriteria implements Criteria {
    @Override
    public List<Employee> meetCriteria (List<Employee> employees) {
        List<Employee> result = new ArrayList<>();
        for (Employee e: employees) {
            if (e.getExperience() >= 5) {
                result.add(e);
            }
        }
        return result;
    }
}

class AndCriteria implements Criteria {
    private final Criteria first;
    private final Criteria second;

    public AndCriteria (Criteria first, Criteria second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public List<Employee> meetCriteria (List<Employee> employees) {
        return second.meetCriteria(first.meetCriteria(employees));
    }
}

class OrCriteria implements Criteria {
    private final Criteria first;
    private final Criteria second;

    public OrCriteria (Criteria first, Criteria second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public List<Employee> meetCriteria (List<Employee> employees) {
        List<Employee> firstList = first.meetCriteria(employees);
        List<Employee> secondList = second.meetCriteria(employees);

        for (Employee e: secondList) {
            if (!firstList.contains(e)) {
                firstList.add(e);
            }
        }

        return firstList;
    }
}

public class Main {
    public static void main (String[] args) {
        List<Employee> employees = List.of(
            new Employee("A", "IT", "Male", 6),
            new Employee("B", "HR", "Female", 4),
            new Employee("C", "IT", "Female", 7),
            new Employee("D", "Finance", "Male", 10)
        );

        Criteria it = new ITCriteria();
        Criteria female = new FemaleCriteria();
        Criteria senior = new SeniorCriteria();

        Criteria seniorIT = new AndCriteria(senior, it);
        Criteria femaleOrIt = new OrCriteria(female, it);

        System.out.println("Senior and IT employee:");
        seniorIT.meetCriteria(employees).forEach(System.out::println);

        System.out.println("Female or IT employee:");
        femaleOrIt.meetCriteria(employees).forEach(System.out::println);
    }
}