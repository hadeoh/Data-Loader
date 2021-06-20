package com.payoneer.dataloader.dtos;

import com.payoneer.dataloader.utils.JsonConverter;
import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
public class JobDto {
    private String requestBody;
    private String url;
    private Integer priorityLevel;
    private HttpMethod httpMethod;

    @Override
    public String toString() {
        return JsonConverter.toJson(this);
    }
}
