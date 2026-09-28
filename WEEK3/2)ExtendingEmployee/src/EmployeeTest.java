class Employee {

    private int empId;
    private String empName;
    private double salary;

    // Constructor
    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    // Getter method
    double getSalary() {
        return salary;
    }
}


// ManagerEmployee extends Employee
class ManagerEmployee extends Employee {

    private double teamBonus;

    // Constructor
    ManagerEmployee(int empId, String empName,
                    double salary, double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    // Calculate effective salary
    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


// InternEmployee extends Employee
class InternEmployee extends Employee {

    private double stipendCap;

    // Constructor
    InternEmployee(int empId, String empName,
                   double salary, double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    // Calculate effective salary
    double effectiveSalary() {

        if (getSalary() < stipendCap) {
            return getSalary();
        }
        else {
            return stipendCap;
        }
    }
}


public class EmployeeTest {

    public static void main(String[] args) {

        // Create employees
        Employee e1 =
                new Employee(101, "Arun", 40000);

        Employee e2 =
                new ManagerEmployee(102, "Rahul", 70000, 8000);

        Employee e3 =
                new InternEmployee(103, "Kiran", 12000, 10000);


        // Check employee type using instanceof
        if (e1 instanceof ManagerEmployee) {

            ManagerEmployee m = (ManagerEmployee) e1;
            System.out.println(
                    "Manager effective pay: Rs " +
                            m.effectiveSalary()
            );

        }
        else if (e1 instanceof InternEmployee) {

            InternEmployee i = (InternEmployee) e1;
            System.out.println(
                    "Intern effective pay: Rs " +
                            i.effectiveSalary()
            );

        }
        else {

            System.out.println(
                    "Plain employee pay: Rs " +
                            e1.getSalary()
            );
        }


        if (e2 instanceof ManagerEmployee) {

            ManagerEmployee m = (ManagerEmployee) e2;

            System.out.println(
                    "Manager effective pay: Rs " +
                            m.effectiveSalary()
            );
        }


        if (e3 instanceof InternEmployee) {

            InternEmployee i = (InternEmployee) e3;

            System.out.println(
                    "Intern effective pay: Rs " +
                            i.effectiveSalary()
            );
        }
    }
}