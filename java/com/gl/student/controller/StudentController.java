package com.gl.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.student.entities.Student;
import com.gl.student.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Student> students= studentService.findAll();
		model.addAttribute("Students",students);
		
		return "list-Students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Student theStudent = new Student();
		
		theModel.addAttribute("Student",theStudent);
		
		return "Student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		
		Student theStudent = studentService.findById(theId);
		
		theModel.addAttribute("Student",theStudent);
		
		return "Student-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("department") String department, @RequestParam("country") String country) {
		
		System.out.println(id);
		Student theStudent;
		if(id!=0) {
			theStudent = studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		}else
			theStudent = new Student(name, department, country);
		studentService.save(theStudent);
		
		return "redirect:/students/list";
		
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		studentService.deleteById(theId);
		
		return "redirect:/students/list";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("name") String name, @RequestParam("department") String department, @RequestParam("country") String country, Model theModel) {
		if (name.trim().isEmpty() && department.trim().isEmpty() && country.trim().isEmpty()) {
			return "redirect:/students/list";
		} else {
			List<Student> theStudents = studentService.searchBy(name,department, country);
			
			theModel.addAttribute("Students",theStudents);
			
			return "list-Students";
		}
	}


}
