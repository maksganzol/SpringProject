package com.tsurkan.dao.hibernateImpl;

import com.tsurkan.dao.CurriculumDao;

import java.util.List;

public class CurriculumDaoHibImpl implements CurriculumDao {
    @Override
    public void addGroup(String group) {

    }

    @Override
    public void addSubjects(String... subject) {

    }

    @Override
    public void addSubjectsToGroup(String group, String... subject) {

    }

    @Override
    public void formJournalForGroup(String group) {

    }

    @Override
    public void setMark(String group, String name, String subject, int mark) {

    }

    @Override
    public List<String> getSubjectsForGroup(String group) {
        return null;
    }

    @Override
    public int getMark(String group, String name, String subject) {
        return 0;
    }
}
