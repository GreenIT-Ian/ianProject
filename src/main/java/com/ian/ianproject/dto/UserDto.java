package com.ian.ianproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonIgnore
    private String id;
    private String email;
    private String name;
    private String password;
    private String statusCode;
    private String cellPhone;
    private boolean isActivated;
    private boolean isDeleted;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String passwordChangeDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String lastLoginDate;
}

