package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Playlists")
@Getter
@Setter
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "playlist_user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "playlist_music",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    Set<Music> musics;
}
