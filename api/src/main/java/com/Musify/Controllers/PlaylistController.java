package com.Musify.Controllers;

import com.Musify.DataRepositories.PlaylistRepository;
import com.Musify.DataTables.Playlists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistRepository playlistRepository;

    public PlaylistController(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @GetMapping("/get/{requestedId}")
    private ResponseEntity<Playlists> getPlaylist(@PathVariable Integer requestedId){
        Optional<Playlists> optPlaylist = playlistRepository.findById(requestedId);
        if(optPlaylist.isPresent()){
            Playlists playlist = optPlaylist.get();
            return ResponseEntity.ok(playlist);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    private ResponseEntity<Void> createPlaylist(@RequestBody Playlists newPlaylist, UriComponentsBuilder ucb){
        Playlists playlist = playlistRepository.save(newPlaylist);
        URI locationOfNewPlaylist = ucb.path("/get/{Id}").buildAndExpand(playlist.getId()).toUri();
        return ResponseEntity.created(locationOfNewPlaylist).build();
    }

    @PutMapping("/update/{requestedId}")
    private ResponseEntity<Void> updatePlaylist(@PathVariable Integer requestedId, @RequestBody Playlists updatePlaylist){
        Optional<Playlists> optPlaylist = playlistRepository.findById(requestedId);
        if(optPlaylist.isPresent()){
            Playlists playlist = optPlaylist.get();
            playlist.setCreatedDate(updatePlaylist.getCreatedDate());
            playlist.setLastUpdatedDate(updatePlaylist.getLastUpdatedDate());
            playlist.setName(updatePlaylist.getName());
            playlist.setType(updatePlaylist.isType());
            playlist.setUsers(updatePlaylist.getUsers());
            playlistRepository.save(playlist);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
