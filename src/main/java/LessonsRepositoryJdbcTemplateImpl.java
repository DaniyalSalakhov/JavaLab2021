import models.Course;
import models.Lesson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.*;

public class LessonsRepositoryJdbcTemplateImpl implements LessonsRepository {

    //language=SQL
    private final static String SQL_SELECT_ALL_BY_DAY_OF_WEEK = "select *, c.id as course_id from lesson left join course as c on c.id = lesson.course where dayofweek = ? order by lesson.id";

    //language=SQL
    private final static String SQL_UPDATE_BY_ID = "update lesson set subject = ?," +
            " dayofweek = ?, course = ? where id = ?";

    //language=SQL
    private final static String SQL_FIND_LESSON_BY_ID = "select *, c.id as course_id from lesson join course as c on c.id = lesson.course where lesson.id = ?";

    //language=SQL
    private final static String SQL_INSERT = "insert into lesson(subject, dayofweek, course) values (?, ?, ?)";

    private JdbcTemplate jdbcTemplate;



    public LessonsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Lesson> lessonRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String subject = row.getString("subject");
        String dayOfWeek = row.getString("dayOfWeek");
        Integer courseId = row.getInt("course");
        CoursesRepository coursesRepository = new CoursesRepositoryJdbcTemplateImpl(jdbcTemplate.getDataSource());
        Course course = coursesRepository.findById(courseId).get();
        Lesson lesson = new Lesson(id, subject, dayOfWeek, course);
        return lesson;
    };

    private final ResultSetExtractor<List<Lesson>> lessonResultSetExtractor = resultSet -> {
        List<Lesson> lessons = new ArrayList<>();
        while(resultSet.next()){
            Integer lessonId = resultSet.getInt("id");
            String subject = resultSet.getString("subject");
            String dayOfWeek = resultSet.getString("dayOfWeek");
            Integer courseId = resultSet.getInt("course_id");
            CoursesRepository coursesRepository = new CoursesRepositoryJdbcTemplateImpl(jdbcTemplate.getDataSource());
            Course course = coursesRepository.findById(courseId).get();
            Lesson lesson = new Lesson(lessonId, subject, dayOfWeek, course);
            lessons.add(lesson);
        }
        return lessons;
    };


    @Override
    public List<Lesson> findAllByDayOfWeek(String searchDayOfWeek) {
        return  jdbcTemplate.query(SQL_SELECT_ALL_BY_DAY_OF_WEEK, lessonRowMapper, searchDayOfWeek);
    }

    @Override
    public void update(Lesson lesson) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, lesson.getSubject(), lesson.getDayOfWeek(), lesson.getCourse().getId(), lesson.getId());
    }

    @Override
    public Optional<Lesson> findById(Integer searchId) {
        try {
            return Optional.of(jdbcTemplate.query(SQL_FIND_LESSON_BY_ID, lessonRowMapper, searchId).get(0));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setString(1, lesson.getSubject());
            statement.setString(2, lesson.getDayOfWeek());
            statement.setInt(3, lesson.getCourse().getId());
            return statement;
        }, keyHolder);
        lesson.setId(keyHolder.getKey().intValue() );
    }


}
