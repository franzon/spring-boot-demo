package com.example.demo.service;

import com.example.demo.dto.CreatePlaylistRequestDto;
import com.example.demo.dto.UpdatePlaylistRequestDto;
import com.example.demo.exception.PlaylistNotFoundException;
import com.example.demo.model.Playlist;
import com.example.demo.model.User;
import com.example.demo.respository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Playlist getPlaylistById(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        return playlist.orElseThrow(() -> new PlaylistNotFoundException(id));
    }

    public Boolean isPlaylistOwnedByUser(Playlist playlist, User user) {
        return playlist.getUser().getId().equals(user.getId());
    }

    public Playlist updatePlaylist(Playlist playlist, UpdatePlaylistRequestDto newData) {
        playlist.setDescription(newData.getDescription());
        return playlistRepository.save(playlist);
    }

    public List<Playlist> listPlaylistsFromUser(User user) {
        return playlistRepository.findByUser(user);
    }
}
