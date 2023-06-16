package com.reservation.tableproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private String userType;
}
