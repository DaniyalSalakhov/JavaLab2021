package models;


import java.util.List;
import java.util.StringJoiner;

public class Course {
    private Integer id;
    private String name;
    private String date;
    private Teacher teacher;
    private List<Student> students;

    public Course(String name, String date, Teacher teacher) {
        this.name = name;
        this.date = date;
        this.teacher = teacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Course.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("date='" + date + "'")
                .add("teacher='" + teacher + "'")
                .add("students=" + students)
                .toString();
    }

    public Course(Integer id, String name, String date, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.teacher = teacher;
    }

    public Course(Integer id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Course(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String name, String date) {
        this.name = name;
        this.date = date;
    }
}
