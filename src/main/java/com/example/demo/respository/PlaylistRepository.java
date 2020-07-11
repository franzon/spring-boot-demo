package com.example.demo.respository;

import java.util.List;

import com.example.demo.model.Playlist;
import com.example.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUser(User user);
}
