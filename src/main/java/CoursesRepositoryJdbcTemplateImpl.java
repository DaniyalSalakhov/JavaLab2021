import models.Course;
import models.Teacher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoursesRepositoryJdbcTemplateImpl implements CoursesRepository {

    //language=SQL
    private final static String SQL_INSERT = "insert into course(name, date, teacher) values (?, ?, ?)";

    //language=SQL
    private final static String SQL_UPDATE_BY_ID = "update course set name = ?," +
            " date = ?, teacher = ? where id = ?";

    //language=SQL
    private final static String SQL_FIND_COURSE_BY_NAME = "select *, t.id as teacher_id from course " +
            "join teacher t on course.teacher = t.id where name = ?";

    //language=SQL
    private final static String SQL_FIND_COURSE_BY_ID = "select *, t.id as teacher_id from course join teacher t on course.teacher = t.id where course.id = ?";


    private JdbcTemplate jdbcTemplate;

    public CoursesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void update(Course course) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, course.getName(), course.getDate(), course.getTeacher().getId(), course.getId());
    }

    /*
    private final RowMapper<Course> courseRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String name = row.getString("name");
        String date = row.getString("date");
        Integer teacherId = row.getInt("teacher_id");
        TeachersRepository teachersRepository = new TeachersRepositoryJdbcTemplateImpl(jdbcTemplate.getDataSource());
        Teacher teacher = teachersRepository.findById(teacherId).get();
        Course course = new Course(id, name, date, teacher);
        return course;
    };
     */

    private final ResultSetExtractor<List<Course>> courseResultSetExtractor = resultSet -> {
        List<Course> courses = new ArrayList<>();
        while(resultSet.next()){
            Integer courseId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String date = resultSet.getString("date");
            Integer teacherId = resultSet.getInt("teacher_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Integer experience = resultSet.getInt("experience");
            Teacher teacher = new Teacher(teacherId, firstName, lastName, experience);
            Course course = new Course(courseId, name, date, teacher);
            courses.add(course);
        }
        return courses;
    };

    @Override
    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{" id "});
            statement.setString(1, course.getName());
            statement.setString(2, course.getDate());
            statement.setInt(3, course.getTeacher().getId());
            return statement;
        }, keyHolder);
        course.setId(keyHolder.getKey().intValue() );
    }

    @Override
    public Optional<Course> findByName(String searchName) {
        try {
            return Optional.of(jdbcTemplate.query(SQL_FIND_COURSE_BY_NAME, courseResultSetExtractor, searchName).get(0));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findById(Integer searchId) {
        try {
            return Optional.of(jdbcTemplate.query(SQL_FIND_COURSE_BY_ID, courseResultSetExtractor, searchId).get(0));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
