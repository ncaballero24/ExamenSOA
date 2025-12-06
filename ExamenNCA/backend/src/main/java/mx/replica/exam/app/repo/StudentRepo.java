package mx.replica.exam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.replica.exam.app.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
