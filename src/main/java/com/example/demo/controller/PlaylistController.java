package com.example.demo.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.dto.AddMusicToPlaylistResponseDto;
import com.example.demo.dto.CreatePlaylistRequestDto;
import com.example.demo.dto.CreatePlaylistResponseDto;
import com.example.demo.dto.PlaylistResponseDto;
import com.example.demo.dto.UpdatePlaylistRequestDto;
import com.example.demo.dto.UpdatePlaylistResponseDto;
import com.example.demo.exception.AccessDeniedException;
import com.example.demo.model.Music;
import com.example.demo.model.Playlist;
import com.example.demo.model.User;
import com.example.demo.service.MusicService;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/playlists")
@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    private final UserService userService;
    private final MusicService musicService;

    @PostMapping("")
    public ResponseEntity<CreatePlaylistResponseDto> createPlaylist(
            @Valid @RequestBody CreatePlaylistRequestDto playlist, Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());

        Playlist newPlaylist = playlistService.createPlaylist(playlist, user);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CreatePlaylistResponseDto response = new CreatePlaylistResponseDto(newPlaylist.getId(),
                newPlaylist.getDescription());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdatePlaylistResponseDto> updatePlaylist(
            @Valid @RequestBody UpdatePlaylistRequestDto newData, @PathVariable("id") Long id, Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());

        Playlist playlist = playlistService.getPlaylistById(id);

        if (!playlistService.isPlaylistOwnedByUser(playlist, user)) {
            throw new AccessDeniedException();
        }

        playlist = playlistService.updatePlaylist(playlist, newData);

        UpdatePlaylistResponseDto response = new UpdatePlaylistResponseDto(playlist.getDescription());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PlaylistResponseDto>> listPlaylists(Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());

        List<PlaylistResponseDto> response = playlistService.listPlaylistsFromUser(user).stream()
                .map((Playlist playlist) -> new PlaylistResponseDto(playlist.getId(), playlist.getDescription(), 0L))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/music/{musicId}")
    public ResponseEntity<AddMusicToPlaylistResponseDto> addMusicToPlaylist(@PathVariable("id") Long playlistId,
            @PathVariable("musicId") Long musicId, Principal principal) {

        User user = userService.loadUserByUsername(principal.getName());

        Playlist playlist = playlistService.getPlaylistById(playlistId);

        if (!playlistService.isPlaylistOwnedByUser(playlist, user)) {
            throw new AccessDeniedException();
        }

        Music music = musicService.getMusicById(musicId);

        playlistService.addMusicToPlaylist(playlist, music);

        AddMusicToPlaylistResponseDto response = new AddMusicToPlaylistResponseDto(playlistId, musicId, new Date());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
