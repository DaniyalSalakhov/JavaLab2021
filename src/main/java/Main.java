import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import models.Course;
import models.Lesson;
import models.Teacher;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }

        HikariConfig config = getHikariConfig(properties);

        DataSource dataSource = new HikariDataSource(config);

        LessonsRepository lessonsRepository = new LessonsRepositoryJdbcTemplateImpl(dataSource);
        CoursesRepository coursesRepository = new CoursesRepositoryJdbcTemplateImpl(dataSource);
        TeachersRepository teachersRepository = new TeachersRepositoryJdbcTemplateImpl(dataSource);

        Scanner sc = new Scanner(System.in);

        findLessonByDayOfWeek(lessonsRepository, sc);

        findLessonById(lessonsRepository, sc);

        findCourseByName(coursesRepository, sc);

        findCourseById(coursesRepository, sc);

        updateLesson(lessonsRepository, coursesRepository, sc);

        saveLesson(lessonsRepository, coursesRepository, sc);

        //Not Implemented Well
        updateCourse(lessonsRepository, coursesRepository, teachersRepository, sc);

        //Not Implemented Well
        saveCourse(coursesRepository, teachersRepository, sc);
    }

    private static void saveCourse(CoursesRepository coursesRepository, TeachersRepository teachersRepository, Scanner sc) {
        System.out.println("enter name of course to save");
        String nameToSave = sc.nextLine();
        System.out.println("enter date 'xx.xx.xxxx/xx.xx.xxxx' to save");
        String date = sc.nextLine();
        System.out.println("enter id of teacher to save. #notImplemented well");
        Integer teacherIdToSave = Integer.parseInt(sc.nextLine());
        Teacher teacherToSave = teachersRepository.findById(teacherIdToSave).get();
        Course courseToSave = new Course(nameToSave, date, teacherToSave);
        coursesRepository.save(courseToSave);
        System.out.println(coursesRepository.findById(courseToSave.getId()).get());
    }

    private static void updateCourse(LessonsRepository lessonsRepository, CoursesRepository coursesRepository, TeachersRepository teachersRepository, Scanner sc) {
        System.out.println("enter searched id to update course");
        Integer searchedIdToUpdate = Integer.parseInt(sc.nextLine());
        System.out.println("enter name to update");
        String nameToUpdate = sc.nextLine();
        System.out.println("enter date 'xx.xx.xxxx/xx.xx.xxxx' to update");
        String dateToUpdate = sc.nextLine();
        System.out.println("enter id of teacher to update. #notImplemented well");
        Integer teacherIdToUpdate = Integer.parseInt(sc.nextLine());
        Teacher teacherToUpdate = teachersRepository.findById(teacherIdToUpdate).get();
        System.out.println("skipped");
        Course courseToUpdate = new Course(searchedIdToUpdate, nameToUpdate, dateToUpdate, teacherToUpdate);
        System.out.println(coursesRepository.findById(courseToUpdate.getId()).get());
        System.out.println("changes:");
        coursesRepository.update(courseToUpdate);
        System.out.println(lessonsRepository.findById(courseToUpdate.getId()).get());
    }

    private static void findCourseById(CoursesRepository coursesRepository, Scanner sc) {
        System.out.println("enter id of course to find");
        Integer courseIdToFind = Integer.parseInt(sc.nextLine());
        System.out.println(coursesRepository.findById(courseIdToFind).get());
    }

    private static void findCourseByName(CoursesRepository coursesRepository, Scanner sc) {
        System.out.println("enter name of course to find");
        String nameOfCourseToFind = sc.nextLine();
        System.out.println(coursesRepository.findByName(nameOfCourseToFind).get());
    }

    private static void saveLesson(LessonsRepository lessonsRepository, CoursesRepository coursesRepository, Scanner sc) {
        System.out.println("enter subject to save");
        String subjectToSave = sc.nextLine();
        System.out.println("enter day of week to save");
        String dayOfWeekToSave = sc.nextLine();
        System.out.println("enter id of course to save");
        Integer courseIdToSave = Integer.parseInt(sc.nextLine());
        Course courseToSave = coursesRepository.findById(courseIdToSave).get();
        Lesson lessonToSave = new Lesson(subjectToSave, dayOfWeekToSave, courseToSave);
        lessonsRepository.save(lessonToSave);
        System.out.println(lessonsRepository.findById(lessonToSave.getId()).get());
    }

    private static void updateLesson(LessonsRepository lessonsRepository, CoursesRepository coursesRepository, Scanner sc) {
        System.out.println("enter searched id to update lesson");
        Integer searchedIdToUpdate = Integer.parseInt(sc.nextLine());
        System.out.println("enter subject to update");
        String subjectToUpdate = sc.nextLine();
        System.out.println("enter day of week to update");
        String dayOfWeekToUpdate = sc.nextLine();
        System.out.println("enter id of course to update");
        Integer courseIdToUpdate = Integer.parseInt(sc.nextLine());
        Course courseToUpdate = coursesRepository.findById(courseIdToUpdate).get();
        Lesson lessonToUpdate = new Lesson(searchedIdToUpdate, subjectToUpdate, dayOfWeekToUpdate, courseToUpdate);
        System.out.println(lessonsRepository.findById(lessonToUpdate.getId()).get());
        System.out.println("changes:");
        lessonsRepository.update(lessonToUpdate);
        System.out.println(lessonsRepository.findById(lessonToUpdate.getId()).get());
    }

    private static void findLessonById(LessonsRepository lessonsRepository, Scanner sc) {
        System.out.println("enter lesson Id to search for lesson");
        Integer searchedLessonId = Integer.parseInt(sc.nextLine());
        System.out.println(lessonsRepository.findById(searchedLessonId).get());
    }

    private static void findLessonByDayOfWeek(LessonsRepository lessonsRepository, Scanner sc) {
        System.out.println("enter day of week to find lessons by day of week");
        String findLessonByDayOfWeek = sc.nextLine();
        System.out.println(lessonsRepository.findAllByDayOfWeek(findLessonByDayOfWeek));
    }

    private static HikariConfig getHikariConfig(Properties properties) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));
        return config;
    }
}
