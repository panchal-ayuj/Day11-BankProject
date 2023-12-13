public class Employee {
    String name;
    String designation;

    public Employee(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
