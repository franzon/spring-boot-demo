package com.example.demo.service;

import com.example.demo.dto.CreatePlaylistRequestDto;
import com.example.demo.model.Playlist;
import com.example.demo.model.User;
import com.example.demo.respository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public Playlist createPlaylist(CreatePlaylistRequestDto playlist, User user) {
        Playlist newPlaylist = new Playlist();
        newPlaylist.setUser(user);
        newPlaylist.setDescription(playlist.getDescription());
        playlistRepository.save(newPlaylist);

        return newPlaylist;
    }
}
