package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.exception.MusicNotFoundException;
import com.example.demo.model.Music;
import com.example.demo.respository.MusicRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    public Music getMusicById(Long id) {
        Optional<Music> music = musicRepository.findById(id);

        return music.orElseThrow(() -> new MusicNotFoundException(id));
    }
}
