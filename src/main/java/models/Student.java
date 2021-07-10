package models;

import java.util.List;
import java.util.StringJoiner;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private String academicGroup;
    private List<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAcademicGroup() {
        return academicGroup;
    }

    public void setAcademicGroup(String academicGroup) {
        this.academicGroup = academicGroup;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("academicGroup='" + academicGroup + "'")
                .add("courses=" + courses)
                .toString();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Student(String firstName, String lastName, String academicGroup, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicGroup = academicGroup;
        this.courses = courses;
    }

    public Student(Integer id, String firstName, String lastName, String academicGroup, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicGroup = academicGroup;
        this.courses = courses;
    }
}
