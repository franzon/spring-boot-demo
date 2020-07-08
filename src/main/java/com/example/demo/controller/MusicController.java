package com.example.demo.controller;

import com.example.demo.dto.MusicResponseDto;
import com.example.demo.model.Music;
import com.example.demo.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/musics")
@RestController
@RequiredArgsConstructor
public class MusicController {
    private final MusicService musicService;

    @GetMapping("")
    public ResponseEntity<List<MusicResponseDto>> getAllMusics() {
        List<Music> musics = musicService.getAllMusics();

        List<MusicResponseDto> response = musics
                .stream()
                .map((Music music) -> new MusicResponseDto(music.getId(), music.getName(), music.getRating()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
