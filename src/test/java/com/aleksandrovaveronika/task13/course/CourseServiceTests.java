package com.aleksandrovaveronika.task13.course;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {
    @InjectMocks
    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;

    @Test
    @DisplayName("Тестирование вывода количества студентов без Mockito")
    void numberOfStudentsWithoutMockitoTest() {
        courseService = new CourseService(new CourseRepository());
        assertEquals(3, courseService.getNumberOfStudents(),
                "The number of students should be 3.");
    }

    @Test
    @DisplayName("Тестирование вывода количества студентов c Mockito")
    void numberOfStudentsMockitoTest() {
        // arrange
        Student student1 = new Student("Ivan", 5.0);
        Student student2 = new Student("Elena", 4.9);

        // act
        Mockito.when(courseRepository.findAll()).thenReturn(List.of(student1,student2));

        // assert
        int actualNumberOfStudents = courseService.getNumberOfStudents();
        assertEquals(2, actualNumberOfStudents);
        Mockito.verify(courseRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Тестирование поиска студента по имени с Mockito")
    void findStudentByNameMockitoTest() {
        // arrange
        Student student3 = new Student("Igor", 5.0);

        // act
        Mockito.when(courseRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(student3));

        // assert
        Student actualStudent = courseService.findStudentByName("Igor");
        assertEquals(student3, actualStudent,
                "Student should have data: name - Igor, score - 5.0");
    }

    @Test
    @DisplayName("Тестирование поиска студента по имени с Mockito Exception")
    void findStudentByNameMockitoExceptionTest() {
        // arrange
        String name = "Maksim";

        // act
        Mockito.doThrow(new StudentNotFoundException(String.format("Student with name %s not found", name)))
                .when(courseRepository).findByName(Mockito.anyString());

        // assert
        Exception exception = assertThrows(StudentNotFoundException.class, () -> courseService.findStudentByName(name),
                "Generation of an exception was related with absence of a student named " + name);
        assertEquals(String.format("Student with name %s not found", name), exception.getMessage(),
                "The message of exception should contain: Student with name ... not found");
    }

    @Test
    @DisplayName("Тестирование поиска студента по имени без Mockito Spy")
    void findStudentByNameMockitoNoSpyTest() {
        // arrange
        String name = "Ivan";

        // не сможет найти Ивана, так как мы не имитировали это поведение и не используем spy, чтобы вернуть реальное значение
        // assert
        Exception exception = assertThrows(StudentNotFoundException.class, () -> courseService.findStudentByName(name),
                "Generation of an exception was related with absence of a student named \" + name");
        assertEquals(String.format("Student with name %s not found", name), exception.getMessage(),
                "The message of exception should contain: Student with name ... not found");
    }

    @Test
    @DisplayName("Тестирование поиска студента по имени c Mockito Spy")
    void findStudentByNameMockitoSpyTest() {
        String name = "Ivan";

        // возьмет студента из реального репозитория несмотря на то, что мы не мокали студентов в репозитории
        CourseRepository courseRepositorySpy = Mockito.spy(new CourseRepository());
        CourseService courseServiceSpy = new CourseService(courseRepositorySpy);
        Student actualStudent = courseServiceSpy.findStudentByName(name);
        Student expectedStudent = new Student(name, actualStudent.getScore());
        assertEquals("Ivan", actualStudent.getName(),
                "Expected student should be Ivan");
        assertEquals(4.7, actualStudent.getScore(),
                "Expected student score should be 4.7");
    }

    @Test
    @DisplayName("Тестирование расчета средней оценки студентов с Mockito")
    void averageScoreMockitoTest() {
        // arrange
        Student student1 = new Student("Ivan", 5.0);
        Student student2 = new Student("Elena", 4.9);

        // act
        Mockito.when(courseRepository.findAll()).thenReturn(List.of(student1, student2));

        // assert
        double actualAverageScore = courseService.getAverageScoreOfAllStudents();
        assertEquals(4.95, actualAverageScore, 0.001,
                "Average score of 2 students with marks 5.0 and 4.9 should be 4.95");
    }

    @Test
    @DisplayName("Тестирование расчета средней оценки студентов с Mockito Exception")
    void averageScoreMockitoException() {
        // arrange
        Mockito.when(courseRepository.findAll()).thenReturn(List.of());

        // act and assert
        Exception exception = assertThrows(RuntimeException.class, () -> courseService.getAverageScoreOfAllStudents(),
                "Should throw RuntimeException when no students found");
        assertEquals("Cannot calculate average score", exception.getMessage(),
                "The message of exception should contain: Cannot calculate average score");
    }

    @Test
    @DisplayName("Тестирование расчета средней оценки студентов с Mockito Spy")
    void averageScoreMockitoSpy() {
        // возьмет студентов из реального репозитория несмотря на то, что мы не мокали студентов в репозитории
        CourseRepository courseRepositorySpy = Mockito.spy(new CourseRepository());
        CourseService courseServiceSpy = new CourseService(courseRepositorySpy);
        double actualScore = courseServiceSpy.getAverageScoreOfAllStudents();
        assertEquals(4.8, actualScore, 0.01,
                "Average score of 3 students from real repository should be (4.7 + 4.9 + 4.8) / 3 = 4.8");
    }
}
