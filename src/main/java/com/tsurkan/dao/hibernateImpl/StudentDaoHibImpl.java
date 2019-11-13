package com.tsurkan.dao.hibernateImpl;

import com.tsurkan.dao.StudentDao;
import com.tsurkan.entities.Student;
import com.tsurkan.entities.hibernateEntities.StudentEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoHibImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Student student) {
        sessionFactory.getCurrentSession().save(new StudentEntity(student));
    }

    @Override
    public Student get(int id) {
        return sessionFactory.getCurrentSession().get(StudentEntity.class, id).toStudent();
    }

    @Override
    public Student getByName(String name) {
        Student[] student = new Student[1];
        getAll().forEach(st -> {
            if(st.getName().equals(name))
                student[0] = st;
        });
        return student[0];
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        for(Object st: sessionFactory.getCurrentSession().createCriteria(StudentEntity.class).list()){
            students.add(((StudentEntity)st).toStudent());
        }
        return students;
    }

    @Override
    public List<Student> getStudentsForGroup(String group) {
        List<Student> students = new ArrayList<>();
        getAll().forEach(student -> {
            if(student.getGroup().equals(group))
                students.add(student);
        });
        return students;
    }
}
