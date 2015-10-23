package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaffi on 10/23/15.
 */
public class StudentsManager {
    private static StudentsManager ourInstance = new StudentsManager();

    public static StudentsManager getInstance() {
        return ourInstance;
    }

    private StudentsManager() {
    }




    //this
    public ObservableList<Student> getStudents(){
        ObservableList<Student> students= FXCollections.observableArrayList();
        students.add(new Student("BIT/1214/201/DT","Michael","Charles","255714095262","201","BIT"));
        students.add(new Student("BIS/1214/201/DT","Osman","Juma","255714095262","201","BIT"));
        students.add(new Student("BCS/374/201/DT","Abdalah","Charles","255714095262","201","BIT"));
        students.add(new Student("BIT/984/201/DT","Achuman","Kipogo","255714095262","201","BIT"));
        students.add(new Student("CUT/1214/201/DT","Chipogo","Charles","255714095262","201","BIT"));
        students.add(new Student("DIT/1214/201/DT","James","Charles","255714095262","201","BIT"));
        students.add(new Student("DCS/1214/201/DT","Silimu","Charles","255714095262","201","BIT"));
        return students;
    }
}
