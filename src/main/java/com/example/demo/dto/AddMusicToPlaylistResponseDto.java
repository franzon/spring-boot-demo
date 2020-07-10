package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AddMusicToPlaylistResponseDto {
    Long playlistId;
    Long musicId;
    Date date;
}
