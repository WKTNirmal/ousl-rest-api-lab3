package com.wktn.Lab2Testing;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/students")
public class StudentApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    private final StudentRepository repository;
    public StudentApplication(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return repository.findAll();
    }

    @PostMapping
    public  Student addStudent(@RequestBody Student student){
        return repository.save(student);
    }

}

//we can put this in a separate file
@Entity
@Table(name = "students")
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String course;

    public Student(){}

    public Student(String course, String name) {this.course = course; this.name = name;}

    public Long getId() {return id;}
    public String getName() {return name;}
    public String getCourse() {return course;}

    public void setName(String name) {this.name = name;}
    public void setCourse(String course) {this.course = course;}



}

interface StudentRepository extends JpaRepository<Student, Long> {}