package com.payoneer.dataloader.services;

import com.payoneer.dataloader.components.RestCaller;
import com.payoneer.dataloader.dtos.JobDto;
import com.payoneer.dataloader.dtos.StudentDto;
import com.payoneer.dataloader.models.Job;
import com.payoneer.dataloader.models.Student;
import com.payoneer.dataloader.repositories.StudentRepository;
import com.payoneer.dataloader.responses.Response;
import com.payoneer.dataloader.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final RestCaller restCaller;

    @Value("${job.url}")
    private String jobUrl;

    @Override
    public Student addStudent(Student student) {
        Student newStudent = null;

        try {
            newStudent = studentRepository.saveAndFlush(student);
        } catch (Exception e) {
            log.error("An error occurred due to {}", e.getMessage());
        }
        return newStudent;
    }

    @Override
    public Job addAsJob(StudentDto studentDto) {
        Response<LinkedHashMap> resp = null;
        try {
            JobDto jobDto = new JobDto();
            jobDto.setUrl("http://localhost:8800/students");
            jobDto.setHttpMethod(HttpMethod.POST);
            jobDto.setPriorityLevel(1);
            jobDto.setRequestBody(studentDto.toString());
            ResponseEntity<String> response = restCaller.makeRequest(jobUrl, jobDto.toString(), HttpMethod.POST);
            resp = JsonConverter.toObj(response.getBody(), Response.class);
        } catch (Exception e) {
            log.error("An error occurred while calling the job service due to {}", e.getMessage());
        }
        assert resp != null;
        return JsonConverter.toObj(JsonConverter.toJson(resp.getData()), Job.class);
    }
}
