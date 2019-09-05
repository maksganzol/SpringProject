package com.company.service;

import com.company.dao.CurriculumDao;
import com.company.dao.StudentDao;
import com.company.dao.springImpl.CurriculumDaoSpringImpl;
import com.company.entities.Student;
import com.company.exception.StudentNotFoundException;
import com.company.exception.SubjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
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

}
