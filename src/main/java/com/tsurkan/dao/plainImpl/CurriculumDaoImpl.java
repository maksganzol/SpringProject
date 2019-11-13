package com.tsurkan.dao.plainImpl;

import com.tsurkan.dao.CurriculumDao;
import com.tsurkan.database.Executer;
import com.tsurkan.database.SQLConnection;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class CurriculumDaoImpl implements CurriculumDao {

    private Executer executer;
    private List<String> subjects;

    public CurriculumDaoImpl(){
        executer = new Executer(SQLConnection.getConnection());
    }

    @Override
    public void addGroup(String group) {
        String query = "INSERT INTO curriculum (grp) VALUES ('" + group + "')";
        executer.update(query);
    }

    public void addSubjects(String... subject) {
        subjects = new ArrayList<>(getSubjects());
        for(String sub: subject)
            subjects.add(sub.toLowerCase());
        this.updateCurriculum();
    }

    @Override
    public void addSubjectsToGroup(String group, String... subject) {
        for(String sub: subject) {
            String query = "UPDATE curriculum SET " + sub.toLowerCase() + "=1 WHERE grp='" + group + "'";
            executer.update(query);
            formJournalForGroup(group);
        }
    }

    @Override
    public void formJournalForGroup(String group) {
        String query;
        String tableName = group.toLowerCase() + "_journal";

        query = "DROP TABLE IF EXISTS `" + tableName + "`";
        executer.update(query);

        query = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (`id` INT NOT NULL AUTO_INCREMENT, " +
                    "stud_name VARCHAR(255) NOT NULL, ";
        List<String> subjects = getSubjectsForGroup(group);
        for (String sub : subjects) {
            query += "`" + sub + "` INT(5), ";
        }

        query += "PRIMARY KEY(`id`))";
        executer.update(query);
        for (String stud : getStudentsForGroup(group)) {
            query = "INSERT INTO `" + tableName + "` (stud_name) VALUES ('" + stud + "')";
            executer.update(query);
        }


    }

    @Override
    public void setMark(String group, String name, String subject, int mark) {
        String query = "UPDATE `" + group.toLowerCase() + "_journal` SET " + subject.toLowerCase() + "=" + mark + " WHERE stud_name='" + name +"'";
        executer.update(query);
    }

    public List<String> getSubjectsForGroup(String group){
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
        return executer.query(query, resultSet -> {
            if(resultSet.next())
                return resultSet.getInt(1);
            return 0;
        });
    }

    public List<String> getStudentsForGroup(String group){
        String query = "SELECT name FROM students WHERE grp='" + group + "'";
        return executer.query(query, resultSet -> {
            List<String> names = new ArrayList<>();
            while (resultSet.next())
                names.add(resultSet.getString(1));
            return names;
        });
    }

    public void updateCurriculum(){
        List<String> difference = getDifferences(getSubjects(), subjects);
        if(difference.size()==0) return;
        String query = "ALTER TABLE curriculum";
        for(String sub: difference) {
            query += " ADD COLUMN `" + sub.toLowerCase() + "` BIT(1) NULL AFTER `grp`";
            if(difference.indexOf(sub)!=difference.size()-1) //if not last item
                query+=",";
        }
        executer.update(query);

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

    public List<String> getSubjects(){
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
}
