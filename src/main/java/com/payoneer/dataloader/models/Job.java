package com.payoneer.dataloader.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
public class Job {

    private Integer id;
    private String requestBody;
    private String url;
    private String status;
    private Integer priorityLevel;
    private HttpMethod httpMethod;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
