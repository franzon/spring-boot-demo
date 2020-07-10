package com.example.demo.service;

import com.example.demo.exception.MusicNotFoundException;
import com.example.demo.exception.PlaylistNotFoundException;
import com.example.demo.model.Music;
import com.example.demo.model.Playlist;
import com.example.demo.respository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
