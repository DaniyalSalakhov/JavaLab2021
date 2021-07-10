import models.Course;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository {
    void update(Course course);
    void save(Course course);
    Optional<Course> findByName(String searchName);
    Optional<Course> findById(Integer searchId);
}
