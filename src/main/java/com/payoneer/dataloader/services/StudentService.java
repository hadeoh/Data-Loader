package com.payoneer.dataloader.services;

import com.payoneer.dataloader.dtos.StudentDto;
import com.payoneer.dataloader.models.Job;
import com.payoneer.dataloader.models.Student;

public interface StudentService {
    Student addStudent(Student student);
    Job addAsJob(StudentDto studentDto);
}
