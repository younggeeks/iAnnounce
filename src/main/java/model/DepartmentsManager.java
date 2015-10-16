package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaffi on 10/15/15.
 */
public class DepartmentsManager {
    private static DepartmentsManager ourInstance = new DepartmentsManager();

    public static DepartmentsManager getInstance() {
        return ourInstance;
    }

    private DepartmentsManager() {
    }


    //this
    public List<Department> getDepartments(){
        List<Department> departments=new ArrayList<>();
        departments.add(new Department("Health Science"));
        departments.add(new Department("Bussiness Administration"));
        departments.add(new Department("Law"));
        departments.add(new Department("Distance Learning"));
        departments.add(new Department("Computer"));

        return departments;
    }
}
