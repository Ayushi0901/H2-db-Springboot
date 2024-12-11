package com.example.Students.service;

import com.example.Students.entity.Student;
import com.example.Students.repo.StudentRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    static List<Student> studentList = new ArrayList<>();

    @Transactional
    @PostConstruct
    void populateList() {
        studentList.add(new Student( "Ayushi", "DPS"));
        studentList.add(new Student( "Ayush", "DAV"));
        studentList.add(new Student( "Ayu", "KV"));
        studentList.add(new Student( "shivi", "Prabhat"));
        studentRepo.saveAll(studentList);
    }

    //get
    public List<Student> getAllStudentName() {
        return studentRepo.findAll();
    }

    //getbyid
    public Optional<Student> getStudentNameById(long id) {
        return studentRepo.findById(id);
    }

    //post
    public Student add(Student st) {
        return studentRepo.save(st);
    }

    //put
    public Student updateById(Student newStudent, long id) {
        Optional<Student> oldStudent = studentRepo.findById(id);
        if (oldStudent.isPresent()) {
//            newStudent.setName("Ritik");
//            newStudent.setSchoolName("AKG");
            Student updatedStudent = oldStudent.get();
            updatedStudent.setName(newStudent.getName());
            updatedStudent.setSchoolName(newStudent.getSchoolName());
            Student student = studentRepo.save(updatedStudent);
            return studentRepo.save(student);
        }
        return null;
    }

    //delete
    public boolean deleteStudentById(long id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;

    }

}
