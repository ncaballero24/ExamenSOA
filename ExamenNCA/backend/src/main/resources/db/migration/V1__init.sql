CREATE TABLE students (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  course VARCHAR(255)
);

INSERT INTO students (name, course) VALUES ('Ana Gomez', 'SOA'), ('Luis Perez', 'REST');
