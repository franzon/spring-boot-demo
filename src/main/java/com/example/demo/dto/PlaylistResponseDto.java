package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaylistResponseDto {
    private Long id;
    private String description;
    private Long musicCount;
}
