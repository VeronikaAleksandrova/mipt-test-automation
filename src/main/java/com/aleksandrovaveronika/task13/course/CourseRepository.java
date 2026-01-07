package com.aleksandrovaveronika.task13.course;

import java.util.List;
import java.util.Optional;

public class CourseRepository {
    public List<Student> findAll() {
        return List.of(
                new Student("Ivan", 4.7),
                new Student("Elena", 4.9),
                new Student("Pavel", 4.8));
    }

    public Optional<Student> findByName(String name) {
        List<Student> students = List.of(new Student("Ivan", 4.7));
        return students.isEmpty() ? Optional.empty() : Optional.of(students.get(0));
    }
}
