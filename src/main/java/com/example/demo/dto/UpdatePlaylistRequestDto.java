package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class UpdatePlaylistRequestDto {
    @NotBlank(message = "É necessário informar uma descrição")
    private String description;
}
