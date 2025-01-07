package com.petshop1018.sungil.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일을 입력하세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
