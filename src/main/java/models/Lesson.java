package models;

import java.util.StringJoiner;

public class Lesson {
    private Integer id;
    private String subject;
    private String dayOfWeek;
    private Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lesson(Integer id, String subject, String dayOfWeek, Course course) {
        this.id = id;
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.course = course;
    }

    public Lesson(String subject, String dayOfWeek, Course course) {
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.course = course;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Lesson.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("subject='" + subject + "'")
                .add("dayOfWeek='" + dayOfWeek + "'")
                .add("course=" + course)
                .toString();
    }
}
