package models;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Teacher {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer experience;
    private List<Course> courses;

    public Teacher(Integer id, String firstName, String lastName, Integer experience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.courses = new ArrayList<>();
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Teacher.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("experience=" + experience)
                .add("courses=" + courses)
                .toString();
    }

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

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Teacher(String firstName, String lastName, Integer experience, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.courses = courses;
    }

    public Teacher(Integer id, String firstName, String lastName, Integer experience, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.courses = courses;
    }
}
