package com.tsurkan.controller;

import com.tsurkan.entities.Student;
import com.tsurkan.exception.StudentNotFoundException;
import com.tsurkan.service.DeaneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private DeaneryService deaneryService;



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView studentList(){
        return new ModelAndView("students", "studList", deaneryService.getAllStudent());
    }

    @GetMapping("/form")
    public ModelAndView form(){
        return new ModelAndView("student-form", "student", new Student());
    }

    @PostMapping(value = "/add")
    public ModelAndView add(@ModelAttribute("student") Student student){
        deaneryService.addStudent(student);
        return new ModelAndView("student-info", "student", student);
    }
    @GetMapping(value = "card-form")
    public ModelAndView  journalForm(){
        return new ModelAndView("card-form", "name", new Student());//Костыль, переделать!
    }

    @GetMapping(value = "/card")
    public ModelAndView card(@RequestParam("name") String name){
        final Map<String, Integer> map;
        List<String> subjectsAndMarks = new ArrayList<>();
        try {
            map = deaneryService.getSubjectToMarkMap(name);
            map.keySet().forEach(subj ->{
                subjectsAndMarks.add(subj + ": " + map.get(subj));
            });
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
        }
        return new ModelAndView("card" , "subList", subjectsAndMarks);
    }

    @GetMapping(value = "get-journal")
    public ModelAndView getJournal(){
        return new ModelAndView("group-form", "student", new Student());
    }
    @PostMapping(value = "journal")
    public ModelAndView journal(@ModelAttribute("student") Student student){

        ModelAndView model = new ModelAndView("journal");
        List<String> subs = deaneryService.getSubjectsForGroup(student.getGroup());
        model.addObject("subjectList", subs);
        List<Student> students = new ArrayList<>();
        for(Student st: deaneryService.getAllStudent())
            if(st.getGroup().equals(student.getGroup())){
                try {
                    st.setCard(deaneryService.getSubjectToMarkMap(st.getName()));
                } catch (StudentNotFoundException e) {
                    e.printStackTrace();
                }
                students.add(st);
            }

        model.addObject("studList", students);

        return model;
    }

    @RequestMapping(value = "grp-journal-ajax", method = RequestMethod.GET)
    @ResponseBody
    public String getGroupJournalWithAjax(HttpServletResponse response) {
        final String[] resp = {""};
        deaneryService.getAllGroups().forEach(group->{
            resp[0] += group + ", ";
        });
        return resp[0];
    }



}
