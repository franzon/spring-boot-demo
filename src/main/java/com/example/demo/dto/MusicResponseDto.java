package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MusicResponseDto {
    private Long id;
    private String name;
    private Float rating;
}
