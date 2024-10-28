package za.org.capaciti.entities;

public class Employee extends Person {
    private String position;
    private double salary;

    public Employee(String name, String id, Address address, String position, double salary) {
        super(name, id, address);
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getDetails() {
        return "Employee: " + name + " (ID: " + id + "), Position: " + position + ", Address: " + address;
    }

    public void giveRaise(double percentage) {
        salary *= (1 + percentage / 100);
        System.out.println(name + " received a " + percentage + "% raise. New salary: $" + salary);
    }
}