package com.tsurkan.service;

import com.tsurkan.dao.CurriculumDao;
import com.tsurkan.dao.StudentDao;
import com.tsurkan.entities.Student;
import com.tsurkan.exception.StudentNotFoundException;
import com.tsurkan.exception.SubjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(value = "transactionManager")
public class DeaneryServiceImpl implements DeaneryService{

    @Autowired
    private CurriculumDao curriculumDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public void setMark(String name, String subject, int mark) throws StudentNotFoundException, SubjectNotFoundException {

        Student student = studentDao.getByName(name);
        if(student==null) throw new StudentNotFoundException(name);

        List<String> subjects = curriculumDao.getSubjectsForGroup(student.getGroup());
        if(!subjects.contains(subject.toLowerCase())) throw new SubjectNotFoundException(subject);

        curriculumDao.setMark(student.getGroup(), student.getName(), subject, mark);
    }

    @Override
    public Map<String, Integer> getSubjectToMarkMap(String name) throws StudentNotFoundException {
        Student student = studentDao.getByName(name);
        if(student==null) throw new StudentNotFoundException(name);
        String group = student.getGroup();
        List<String> subjects = curriculumDao.getSubjectsForGroup(group);
        Map<String, Integer> subjectToMark = new HashMap<>();
        for(String sub: subjects)
            subjectToMark.put(sub, curriculumDao.getMark(group, name, sub));
        return subjectToMark;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentDao.getAll();
    }

    @Override
    public List<String> getSubjectsForGroup(String group) {
        return curriculumDao.getSubjectsForGroup(group);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.add(student);
    }

    @Override
    public Set<String> getAllGroups(){
        Set<String> groups = new HashSet<>();
        studentDao.getAll().forEach(student -> {
            groups.add(student.getGroup());
        });
        return groups;
    }

}
