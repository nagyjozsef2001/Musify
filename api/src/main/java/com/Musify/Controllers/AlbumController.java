package com.Musify.Controllers;

import com.Musify.DataRepositories.AlbumRepository;
import com.Musify.DataTables.Albums;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/get/{requestedId}")
    private ResponseEntity<Albums> getAlbum(@PathVariable Integer requestedId){
        Optional<Albums> optAlbum = albumRepository.findById(requestedId);
        if(optAlbum.isPresent()){
            Albums album = optAlbum.get();
            return ResponseEntity.ok(album);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    private ResponseEntity<Void> createAlbum(@RequestBody Albums newAlbum, UriComponentsBuilder ucb){
        Albums album = albumRepository.save(newAlbum);
        URI locationOfNewAlbum = ucb.path("/get/{Id}").buildAndExpand(album.getId()).toUri();
        return ResponseEntity.created(locationOfNewAlbum).build();
    }

    @PutMapping("/update/{requestedId}")
    private ResponseEntity<Void> updateAlbum(@PathVariable Integer requestedId, @RequestBody Albums updateAlbum){
        Optional<Albums> optAlbum = albumRepository.findById(requestedId);
        if(optAlbum.isPresent()){
            Albums album = optAlbum.get();
            album.setArtists(updateAlbum.getArtists());
            album.setDescription(updateAlbum.getDescription());
            album.setLabel(updateAlbum.getLabel());
            album.setTitle(updateAlbum.getTitle());
            album.setReleaseDate(updateAlbum.getReleaseDate());
            albumRepository.save(album);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
