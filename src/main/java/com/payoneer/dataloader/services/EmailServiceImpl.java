package com.payoneer.dataloader.services;

import com.payoneer.dataloader.components.RestCaller;
import com.payoneer.dataloader.dtos.EmailDto;
import com.payoneer.dataloader.dtos.JobDto;
import com.payoneer.dataloader.models.Job;
import com.payoneer.dataloader.responses.Response;
import com.payoneer.dataloader.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final RestCaller restCaller;

    @Value("${job.url}")
    private String jobUrl;


    @Override
    public void sendEmail(EmailDto emailDto) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //mailMessage.setFrom(emailDto.getFromEmail());
        mailMessage.setTo(emailDto.getToEmail());
        mailMessage.setSubject(emailDto.getSubject());
        mailMessage.setText(emailDto.getEmailBody());
        log.info("The mail is {}", mailMessage);
        javaMailSender.send(mailMessage);
    }

    @Override
    public Job addEmailJob(EmailDto emailDto) {
        Response<LinkedHashMap> resp = null;
        try {
            JobDto jobDto = new JobDto();
            jobDto.setUrl("http://localhost:8800/sendEmail");
            jobDto.setHttpMethod(HttpMethod.POST);
            jobDto.setPriorityLevel(2);
            jobDto.setRequestBody(emailDto.toString());
            ResponseEntity<String> response = restCaller.makeRequest(jobUrl, jobDto.toString(), HttpMethod.POST);
            resp = JsonConverter.toObj(response.getBody(), Response.class);
        } catch (Exception e) {
            log.error("An error occurred while calling the job service due to {}", e.getMessage());
        }
        assert resp != null;
        return JsonConverter.toObj(JsonConverter.toJson(resp.getData()), Job.class);
    }
}
