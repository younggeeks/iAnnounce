package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Gaffi on 10/15/15.
 */
public class Department {

    private SimpleStringProperty departmentName;

    public Department(String departmentName) {
        this.departmentName = new SimpleStringProperty(departmentName);
    }

    public String getDepartmentName() {
        return departmentName.get();
    }

    public SimpleStringProperty departmentNameProperty() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName.set(departmentName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
