package com.payoneer.dataloader.dtos;

import com.payoneer.dataloader.utils.JsonConverter;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmailDto {

    @NotEmpty
    @NotNull
    private String fromEmail;

    @NotEmpty
    @NotNull
    private String toEmail;

    @NotEmpty
    @NotNull
    private String subject;

    @NotEmpty
    @NotNull
    private String emailBody;


    @Override
    public String toString() {
        return JsonConverter.toJson(this);
    }
}
