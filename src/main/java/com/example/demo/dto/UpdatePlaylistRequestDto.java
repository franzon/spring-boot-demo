package com.example.demo.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdatePlaylistRequestDto {
    @NotBlank(message = "É necessário informar uma descrição")
    private String description;
}
