package com.company.dao;

import com.company.entities.Student;

import java.util.List;

public interface StudentDao {
    void add(Student student);
    Student get(int id);
    Student getByName(String name);
    List<Student> getAll();
    List<Student> getStudentsForGroup(String group);


}
