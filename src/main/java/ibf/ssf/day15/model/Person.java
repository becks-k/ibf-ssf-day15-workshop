package ibf.ssf.day15.model;

public class Person {
    
    private int id;
    private String fullName;
    private int salary;
    
    
    public Person() {
    }


    public Person(int id, String fullName, int salary) {
        this.id = id;
        this.fullName = fullName;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public int getSalary() {
        return salary;
    }


    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Comma separated values
    @Override
    public String toString() {
        return id + "," + fullName + "," + salary;
    }

    
}
