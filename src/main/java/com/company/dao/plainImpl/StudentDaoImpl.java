package com.company.dao.plainImpl;

import com.company.dao.CurriculumDao;
import com.company.dao.StudentDao;
import com.company.database.Executer;
import com.company.database.SQLConnection;
import com.company.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private Executer executer;

    private CurriculumDao curriculumDao;

    public StudentDaoImpl(){
        executer = new Executer(SQLConnection.getConnection());
        curriculumDao = new CurriculumDaoImpl();
    }

    @Override
    //@Transactional
    public void add(Student student) {
        String query = "INSERT INTO students (name, grp, age, phone) VALUES ('" +
                student.getName() + "', '" + student.getGroup() + "', " + "" + student.getAge() + ", '" +
                student.getPhone() + "')";
        executer.update(query);
        String group = student.getGroup();
        curriculumDao.addGroup(group);
        curriculumDao.formJournalForGroup(group);
        query = "INSERT INTO `" + group.toLowerCase() + "_journal` (stud_name) VALUES('" + student.getName() + "')";
        executer.update(query);
    }

    @Override
    public Student get(int id) {
        String query = "SELECT * FROM students WHERE idstudents=" + id;
        return executer.query(query, resultSet -> {
            if(resultSet.next()) {
                String name = resultSet.getString("name"),
                        group = resultSet.getString("grp"),
                        phone = resultSet.getString("phone");
                int age = resultSet.getInt("age");
                return new Student(name, group, age, phone);
            }
            return null;
        });

    }

    @Override
    public Student getByName(String name) {
        String query = "SELECT * FROM students WHERE name='" + name + "'";
        return executer.query(query, resultSet -> {
            if(resultSet.next()) {
                String group = resultSet.getString("grp"), phone = resultSet.getString("phone");
                int age = resultSet.getInt("age");
                return new Student(name, group, age, phone);
            }
            return null;
        });
    }

    @Override
    public List<Student> getAll() {
        String query = "SELECT * FROM students";
        return executer.query(query, resultSet -> {
            List<Student> studs = new ArrayList<>();
            while(resultSet.next()) {
                String name = resultSet.getString("name"),
                group = resultSet.getString("grp"),
                phone = resultSet.getString("phone");
                int age = resultSet.getInt("age");
                studs.add(new Student(name, group, age, phone));
            }
            return studs;
        });
    }

    @Override
    public List<Student> getStudentsForGroup(String group) {
        List<Student> stds = new ArrayList<>();
        for(Student st:getAll())
            if(st.getGroup().equals(group))
                stds.add(st);
        return stds;
    }
}
