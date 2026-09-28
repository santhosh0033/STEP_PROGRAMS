class Employee {

    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}


class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to " + slotNo);
        }
    }
}


class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, ParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {
            ManagerEmployee manager = (ManagerEmployee) employee;
            pay = manager.effectiveSalary();
        }
        else {
            pay = employee.getSalary();
        }

        if (slot != null) {
            return name + " | Pay: Rs " + pay +
                    " | Slot: " + slot.slotNo;
        }
        else {
            return name + " | Pay: Rs " + pay +
                    " | Slot: no parking assigned";
        }
    }
}


public class CompanySystem {

    public static void main(String[] args) {

        ParkingSlot slot1 = new ParkingSlot("A1", 4, 0);
        ParkingSlot slot2 = new ParkingSlot("A2", 4, 0);

        Employee e1 =
                new ManagerEmployee(101, "Divya", 70000, 8000);

        Employee e2 =
                new Employee(102, "Karan", 40000);

        Employee e3 =
                new Employee(103, "Meera", 10000);

        slot1.allot("E101");
        slot2.allot("E102");

        CompanyEmployeeRecord r1 =
                new CompanyEmployeeRecord("Divya", "E101", e1, slot1);

        CompanyEmployeeRecord r2 =
                new CompanyEmployeeRecord("Karan", "E102", e2, slot2);

        CompanyEmployeeRecord r3 =
                new CompanyEmployeeRecord("Meera", "E103", e3, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());

        System.out.println("Total records: " +
                CompanyEmployeeRecord.totalRecords);
    }
}