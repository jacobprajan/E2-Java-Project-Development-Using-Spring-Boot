package com.dev.student.dal;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dev.student.dal.entities.Student;
import com.dev.student.dal.repos.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDalApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	public void testCreateStudent() {
		
		Student student = new Student();
		student.setName("John");
		student.setCourse("Java webservice");
		student.setFee(30d);
		
		studentRepository.save(student);
	}

	@Test
	public void testFindStudentById() {
		Optional<Student> studentById = studentRepository.findById(1L);
		System.out.println(studentById.get());		
	}
	
	@Test
	public void testUpdateStudent() {
		Optional<Student> studentById = studentRepository.findById(1L);
		studentById.get().setFee(60d);
		studentRepository.save(studentById.get());
	}
	
	@Test
	public void testDeleteStudent() {
		Student student = new Student();
		student.setId(1L);
		
		studentRepository.delete(student);
	}
}
