package com.tsurkan.dao;

import java.util.List;

public interface CurriculumDao {

    void addGroup(String group);
    void addSubjects(String... subject);
    void addSubjectsToGroup(String group, String... subject);
    void formJournalForGroup(String group);
    void setMark(String group, String name, String subject, int mark);
    public List<String> getSubjectsForGroup(String group);
    int getMark(String group, String name, String subject);
}
