package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaffi on 10/16/15.
 */
public class CoursesManager {
    private static CoursesManager ourInstance = new CoursesManager();

    public static CoursesManager getInstance() {
        return ourInstance;
    }

    private CoursesManager() {
    }

    public ObservableList<Course> getComputerCourses(){
        ObservableList<Course> courses= FXCollections.observableArrayList();

        courses.add(new Course("BIT","Computer"));
        courses.add(new Course("BCS","Computer"));
        courses.add(new Course("BCE","Computer"));
        courses.add(new Course("BIS","Computer"));

        return courses;

    }
    public ObservableList<Course> getShsCourses(){
        ObservableList<Course> courses= FXCollections.observableArrayList();

        courses.add(new Course("Physiology","Health Sciences"));
        courses.add(new Course("Medical Lab","Health Sciences"));
        courses.add(new Course("aNaTomy","Health Sciences"));
        courses.add(new Course("LAb","Health Sciences"));

        return courses;

    }
    public ObservableList<Intake> getIntakes(){
        ObservableList<Intake> intakes= FXCollections.observableArrayList();

        intakes.add(new Intake("101"));
        intakes.add(new Intake("202"));
        intakes.add(new Intake("301"));
        intakes.add(new Intake("401"));


        return intakes;

    }




}
