package com.dev.student.dal.repos;

import org.springframework.data.repository.CrudRepository;

import com.dev.student.dal.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
