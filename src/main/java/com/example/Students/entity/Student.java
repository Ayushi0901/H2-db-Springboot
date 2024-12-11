package com.example.Students.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String name;
    public String schoolName;

    public Student(long id, String name, String schoolName) {
        this.id = id;
        this.name = name;
        this.schoolName = schoolName;
    }

    public Student() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
