package com.example.Students.controller;

import com.example.Students.entity.Student;
import com.example.Students.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    public StudentService studentService;

    @Operation(summary = "Get all student available", description = "Get Student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })


    @GetMapping("/getStudentSchoolDetails")
    public ResponseEntity<List<Student>> getStudentSchool() {
        List<Student> studentList = new ArrayList<>();
        studentList = studentService.getAllStudentName();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/getStudentSchoolDetails/{id}")
    public ResponseEntity<Student> getStudentSchoolById(@PathVariable long id) {
        Optional<Student> student = studentService.getStudentNameById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@PathVariable Student st) {
        Student studentObj = studentService.add(st);
        return new ResponseEntity<>(studentObj, HttpStatus.CREATED);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        Student student = studentService.updateById(newStudent, id);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {

        boolean studentDeleted = studentService.deleteStudentById(id);
        if (studentDeleted == true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
