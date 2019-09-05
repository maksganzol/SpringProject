package com.company.dao.springImpl;

import com.company.dao.CurriculumDao;
import com.company.dao.springImpl.rowMapper.*;
import com.company.database.Executer;
import com.company.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CurriculumDaoSpringImpl implements CurriculumDao {

    @Autowired
    private Executer executer;
    @Autowired
    private JdbcTemplate template;

    private ArrayList<String> subjects;

    @Override
    public void addGroup(String group) {
        String query = "INSERT INTO curriculum (grp) VALUES ('" + group + "')";
        template.execute(query);
    }

    @Override
    public void addSubjects(String... subject) {
        subjects = new ArrayList<>(getSubjects());
        for(String sub: subject)
            subjects.add(sub.toLowerCase());
        this.updateCurriculum();

    }

    private void updateCurriculum() {
        List<String> difference = getDifferences(getSubjects(), subjects);
        if(difference.size()==0) return;
        String query = "ALTER TABLE curriculum";
        for(String sub: difference) {
            query += " ADD COLUMN `" + sub.toLowerCase() + "` BIT(1) NULL AFTER `grp`";
            if(difference.indexOf(sub)!=difference.size()-1) //if not last item
                query+=",";
        }
        template.execute(query);
    }
    /**
     * This function finds different items in old subject list and new
     * @return list of different items
     */
    private List<String> getDifferences(List<String> oldList, List<String> newList){
        List<String> differences = new ArrayList<>();
        for(String sub: newList)
            if(!oldList.contains(sub))
                differences.add(sub);
        return differences;
    }

    private List<String> getSubjects() { //Говнокод без поддержки Spring JdbcTemplate
        String query = "SELECT * FROM curriculum";
        return executer.query(query, resultSet -> {
            List<String> subjects = new ArrayList<>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for(int i = 3; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                subjects.add(columnName);
            }
            return subjects;
        });
    }

    @Override
    public void addSubjectsToGroup(String group, String... subject) {
        for(String sub: subject) {
            String query = "UPDATE curriculum SET " + sub.toLowerCase() + "=1 WHERE grp='" + group + "'";
            template.execute(query);
            formJournalForGroup(group);
        }

    }

    @Override
    @Transactional
    public void formJournalForGroup(String group) {
        String query;
        String tableName = group.toLowerCase() + "_journal";

        query = "DROP TABLE IF EXISTS `" + tableName + "`";
        template.execute(query);

        query = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (`id` INT NOT NULL AUTO_INCREMENT, " +
                "stud_name VARCHAR(255) NOT NULL, ";
        List<String> subjects = getSubjectsForGroup(group);
        for (String sub : subjects) {
            query += "`" + sub + "` INT(5), ";
        }

        query += "PRIMARY KEY(`id`))";
        template.execute(query);
        for (Student stud : getStudentsForGroup(group)) {
            query = "INSERT INTO `" + tableName + "` (stud_name) VALUES ('" + stud.getName() + "')";
            template.execute(query);
        }

    }

    public List<Student> getStudentsForGroup(String group){
        String query = "SELECT * FROM students WHERE grp='" + group + "'";
        return template.query(query, new StudentRowMapper());
    }

    @Override
    public void setMark(String group, String name, String subject, int mark) {
        String query = "UPDATE `" + group.toLowerCase() + "_journal` SET " + subject.toLowerCase() + "=" + mark + " WHERE stud_name='" + name +"'";
        template.execute(query);
    }

    @Override
    public List<String> getSubjectsForGroup(String group) {//Говнокод того же типа что и в getSubjects()
        String query = "SELECT * FROM curriculum WHERE grp='" + group.toLowerCase() + "'";
        return executer.query(query, resultSet -> {
            List<String> list = new ArrayList<>();
            if(resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int collumnCount = metaData.getColumnCount();
                for (int i = 3; i <= collumnCount; i++) {
                    if (resultSet.getBoolean(i))
                        list.add(metaData.getColumnName(i));
                }
            }
            return list;
        });
    }

    @Override
    public int getMark(String group, String name, String subject) {
        String query = "SELECT " + subject.toLowerCase() + " FROM `" + group.toLowerCase() + "_journal` WHERE stud_name='" + name + "'";
        return template.queryForObject(query, new MarkRowMapper());
    }
}
