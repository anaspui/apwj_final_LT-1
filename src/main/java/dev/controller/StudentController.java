package dev.controller;

import dev.domain.Student;
import dev.service.StudentService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @InitBinder
    public void intiBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/students")
    public String getAllStudents(Model model) throws SQLException {
        Object students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }
    @RequestMapping("/students/{id}")
    public String getStudent(Model model, @PathVariable int id) throws SQLException {
        Object students = studentService.getOne(id);
        model.addAttribute("students", students);
        return "students";
    }

    @RequestMapping("/students/{id}/edit")
    public String editStudent(Model model, @PathVariable int id) throws SQLException {
        Student student = studentService.getOne(id);
        model.addAttribute("student", student);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String confirmUpdate(@ModelAttribute("student") Student student, @PathVariable int id) throws SQLException {
        studentService.edit(student);
        return "redirect:/students";
    }

    @RequestMapping("/students/{id}/delete")
    public String deleteStudent(Model model, @PathVariable int id) throws SQLException {
        studentService.delete(id);
        Object students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }
}
