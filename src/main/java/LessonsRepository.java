import models.Course;
import models.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonsRepository {
    List<Lesson> findAllByDayOfWeek(String searchDayOfWeek);
    void save(Lesson lesson);
    void update(Lesson lesson);
    Optional<Lesson> findById(Integer searchId);
}
