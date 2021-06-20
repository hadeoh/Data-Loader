package com.payoneer.dataloader.controllers;

import com.payoneer.dataloader.dtos.EmailDto;
import com.payoneer.dataloader.models.Job;
import com.payoneer.dataloader.responses.Response;
import com.payoneer.dataloader.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<Response<EmailDto>> sendEmail(@Valid @RequestBody EmailDto emailDto) {
        emailService.sendEmail(emailDto);
        Response<EmailDto> response = new Response<>();
        response.setMessage("Message sent successfully");
        response.setStatus(HttpStatus.OK);
        response.setData(emailDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/email-service")
    public ResponseEntity<Response<Job>> addEmailJob(@Valid @RequestBody EmailDto emailDto) {
        Job job = emailService.addEmailJob(emailDto);
        Response<Job> response = new Response<>();
        response.setMessage("Successfully added as a job on the queue");
        response.setStatus(HttpStatus.CREATED);
        response.setData(job);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
