package com.payoneer.dataloader.dtos;

import com.payoneer.dataloader.utils.JsonConverter;
import lombok.Data;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class StudentDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotNull
    private int classNumber;

    @Override
    public String toString() {
        return JsonConverter.toJson(this);
    }
}
