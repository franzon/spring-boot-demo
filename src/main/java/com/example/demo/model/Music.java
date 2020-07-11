package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Musics")
@Getter
@Setter
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Float rating;

    @ManyToMany(mappedBy = "musics")
    Set<Playlist> playlists;
}
