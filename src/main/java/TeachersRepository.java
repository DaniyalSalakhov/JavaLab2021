import models.Teacher;

import java.util.Optional;

public interface TeachersRepository {
    Optional<Teacher> findById(Integer searchId);
}
