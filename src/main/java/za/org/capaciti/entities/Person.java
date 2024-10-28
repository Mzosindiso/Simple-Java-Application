package za.org.capaciti.entities;

public abstract class Person {
    protected String name;
    protected String id;
    protected Address address;

    public Person(String name, String id, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address=" + address +
                '}';
    }

    public abstract String getDetails();
}