package com.example.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddMusicToPlaylistResponseDto {
    Long playlistId;
    Long musicId;
    Date date;
}
