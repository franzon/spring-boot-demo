package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Utilize um email válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Min(value = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String password;
}
