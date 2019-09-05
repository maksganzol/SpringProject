package com.company.dao.springImpl;

import com.company.dao.CurriculumDao;
import com.company.dao.StudentDao;
import com.company.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.company.dao.springImpl.rowMapper.StudentRowMapper;

import java.util.List;

@Repository
public class StudentDaoSpringImpl implements StudentDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private CurriculumDao curriculumDao;

    @Override
    @Transactional
    public void add(Student student) {
        String query = "INSERT INTO students (name, grp, age, phone) VALUES ('" +
                student.getName() + "', '" + student.getGroup() + "', " + "" + student.getAge() + ", '" +
                student.getPhone() + "')";
        template.execute(query);
        String group = student.getGroup();
        curriculumDao.addGroup(group);
        curriculumDao.formJournalForGroup(group);
    }

    @Override
    public Student get(int id) {
        String query = "SELECT * FROM students WHERE id=" + id;
        return  template.queryForObject(query, new StudentRowMapper());
    }

    @Override
    public Student getByName(String name) {
        String query = "SELECT * FROM students WHERE name='" + name + "'";
        return  template.queryForObject(query, new StudentRowMapper());
    }

    @Override
    public List<Student> getAll() {
        String query = "SELECT * FROM students";
        return  template.query(query, new StudentRowMapper());
    }

    @Override
    public List<Student> getStudentsForGroup(String group) {
        String query = "SELECT * FROM students WHERE grp='" + group + "'";
        return  template.query(query, new StudentRowMapper());
    }
}
