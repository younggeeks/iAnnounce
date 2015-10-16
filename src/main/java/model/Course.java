package model;

/**
 * Created by Gaffi on 10/16/15.
 */
public class Course {

    private String name;
    private String department;

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
