package com.company.controller;

import com.company.entities.Student;
import com.company.exception.StudentNotFoundException;
import com.company.service.DeaneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/card")
    public ModelAndView card(@ModelAttribute("name") Student name){//Тоже самое
        final Map<String, Integer> map;
        List<String> subjectsAndMarks = new ArrayList<>();
        try {
            map = deaneryService.getSubjectToMarkMap(name.getName());
            map.keySet().forEach(subj ->{
                subjectsAndMarks.add(subj + ": " + map.get(subj));
            });
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
        }
        return new ModelAndView("card" , "subList", subjectsAndMarks);
    }







}
