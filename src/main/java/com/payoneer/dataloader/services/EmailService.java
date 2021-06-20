package com.payoneer.dataloader.services;

import com.payoneer.dataloader.dtos.EmailDto;
import com.payoneer.dataloader.models.Job;

public interface EmailService {
    void sendEmail(EmailDto emailDto);
    Job addEmailJob(EmailDto emailDto);
}
