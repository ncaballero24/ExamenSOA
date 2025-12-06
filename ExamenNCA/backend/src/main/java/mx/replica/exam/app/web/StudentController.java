package mx.replica.exam.app.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.replica.exam.app.model.Student;
import mx.replica.exam.app.service.StudentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    @GetMapping
    public List<Student> list() { return service.all(); }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student s = service.find(id);
        return s == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student s) {
        Student created = service.create(s);
        return ResponseEntity.created(URI.create("/api/students/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student s) {
        Student updated = service.update(id, s);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
