package com.tsurkan.service;

import com.tsurkan.entities.Student;
import com.tsurkan.exception.StudentNotFoundException;
import com.tsurkan.exception.SubjectNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DeaneryService {

    void setMark(String name, String subject, int mark) throws StudentNotFoundException, SubjectNotFoundException;
    Map<String, Integer> getSubjectToMarkMap(String name) throws StudentNotFoundException;
    List<Student> getAllStudent();
    List<String> getSubjectsForGroup(String group);
    void addStudent(Student student);

    Set<String> getAllGroups();


}
