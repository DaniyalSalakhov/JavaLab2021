import models.Course;
import models.Lesson;
import models.Teacher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.*;

public class TeachersRepositoryJdbcTemplateImpl implements TeachersRepository {
    //language=SQL
    private final static String SQL_FIND_LESSON_BY_ID = "select *, c.id as course_id from teacher join course c on teacher.id = c.teacher where teacher.id = ?";

    private JdbcTemplate jdbcTemplate;

    public TeachersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<Teacher> teacherResultSetExtractor = resultSet -> {
        Map<Teacher, List<Course>> map = new HashMap<>();
        Teacher teacher = null;
        while(resultSet.next()){
            Integer teacherId = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Integer experience = resultSet.getInt("experience");
            teacher = new Teacher(teacherId, firstName, lastName, experience);
            map.putIfAbsent(teacher, new ArrayList<>());
            Integer courseId = resultSet.getInt("course_id");
            CoursesRepository coursesRepository = new CoursesRepositoryJdbcTemplateImpl(jdbcTemplate.getDataSource());
            Course course = coursesRepository.findById(courseId).get();
            map.get(teacher).add(course);
        }
        teacher.setCourses(map.get(teacher));
        return teacher;
    };


    @Override
    public Optional<Teacher> findById(Integer searchId) {
        try {
            return Optional.of(jdbcTemplate.query(SQL_FIND_LESSON_BY_ID, teacherResultSetExtractor, searchId));
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
