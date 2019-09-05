package com.company.service;

import com.company.entities.Student;
import com.company.exception.StudentNotFoundException;
import com.company.exception.SubjectNotFoundException;

import java.util.List;
import java.util.Map;

public interface DeaneryService {

    void setMark(String name, String subject, int mark) throws StudentNotFoundException, SubjectNotFoundException;
    Map<String, Integer> getSubjectToMarkMap(String name) throws StudentNotFoundException;
    List<Student> getAllStudent();
    List<String> getSubjectsForGroup(String group);
    void addStudent(Student student);



}
