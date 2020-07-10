package com.example.demo.controller;

import com.example.demo.dto.CreatePlaylistRequestDto;
import com.example.demo.dto.CreatePlaylistResponseDto;
import com.example.demo.dto.UpdatePlaylistRequestDto;
import com.example.demo.dto.UpdatePlaylistResponseDto;
import com.example.demo.exception.AccessDeniedException;
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
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("{id}")
    public ResponseEntity<UpdatePlaylistResponseDto> updatePlaylist(@Valid @RequestBody UpdatePlaylistRequestDto newData,
                                                                    @PathVariable("id") Long id,
                                                                    Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());

        Playlist playlist = playlistService.getPlaylistById(id);

        if (!playlistService.isPlaylistOwnedByUser(playlist, user)) {
            throw new AccessDeniedException();
        }

        playlist = playlistService.updatePlaylist(playlist, newData);

        UpdatePlaylistResponseDto response = new UpdatePlaylistResponseDto(playlist.getDescription());
        return new ResponseEntity<>(response, HttpStatus.OK);

        // Verificar se playlist existe - ok
        // Verificar se playlist pertence ao usuário
        // Atualizar
        // Retornar
    }

}
