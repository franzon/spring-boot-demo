package com.example.demo.controller;

import com.example.demo.dto.CreatePlaylistRequestDto;
import com.example.demo.dto.CreatePlaylistResponseDto;
import com.example.demo.model.Playlist;
import com.example.demo.model.User;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/playlists")
@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<CreatePlaylistResponseDto> createPlaylist(@Valid @RequestBody CreatePlaylistRequestDto playlist, Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());

        Playlist newPlaylist = playlistService.createPlaylist(playlist, user);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CreatePlaylistResponseDto response = new CreatePlaylistResponseDto(newPlaylist.getId(), newPlaylist.getDescription());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
