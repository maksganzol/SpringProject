package com.company.dao.springImpl.rowMapper;

import com.company.entities.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setAge(resultSet.getInt("age"));
        student.setName(resultSet.getString("name"));
        student.setGroup(resultSet.getString("grp"));
        student.setPhone(resultSet.getString("phone"));
        return student;
    }
}
