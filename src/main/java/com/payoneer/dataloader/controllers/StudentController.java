package com.payoneer.dataloader.controllers;

import com.payoneer.dataloader.dtos.StudentDto;
import com.payoneer.dataloader.models.Job;
import com.payoneer.dataloader.models.Student;
import com.payoneer.dataloader.responses.Response;
import com.payoneer.dataloader.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @PostMapping("/students")
    public ResponseEntity<Response<Student>> addStudent(@Valid @RequestBody StudentDto studentDto) {
        Student student = studentService.addStudent(modelMapper.map(studentDto, Student.class));
        Response<Student> response = new Response<>();
        if (student != null && !student.getFirstName().isEmpty()) {
            response.setData(student);
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("Student successfully created");
        } else {
            response.setStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage("Unable to process request of adding student");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/job-service")
    public ResponseEntity<Response<Job>> addAsJob(@Valid @RequestBody StudentDto studentDto) {
        Job job = studentService.addAsJob(studentDto);
        Response<Job> response = new Response<>();
        response.setMessage("Successfully added as a job on the queue");
        response.setStatus(HttpStatus.CREATED);
        response.setData(job);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
