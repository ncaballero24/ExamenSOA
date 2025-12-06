package mx.replica.exam.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.replica.exam.app.model.Student;
import mx.replica.exam.app.repo.StudentRepo;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepo repo;

    public StudentService(StudentRepo repo) { this.repo = repo; }

    public List<Student> all() { return repo.findAll(); }

    public Student find(Long id) { return repo.findById(id).orElse(null); }

    public Student create(Student s) { return repo.save(s); }

    public Student update(Long id, Student s) {
        return repo.findById(id).map(existing -> {
            existing.setName(s.getName());
            existing.setCourse(s.getCourse());
            return repo.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) { repo.deleteById(id); }
}
