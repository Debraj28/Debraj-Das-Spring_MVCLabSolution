package com.gl.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.student.entities.Student;


@Service
public interface StudentService {
	
	List<Student> findAll();
	
	Student findById(int id);
	
	void save(Student student);
	
	void deleteById(int id);
	
	List<Student> searchBy(String name, String department, String country);

}
