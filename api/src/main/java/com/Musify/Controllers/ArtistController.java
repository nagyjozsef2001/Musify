package com.Musify.Controllers;

import com.Musify.DataRepositories.ArtistRepository;
import com.Musify.DataTables.Artists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/artist") //just admins supposed to be able to access this path
public class ArtistController {
    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/get/{requestedId}")
    private ResponseEntity<Artists> getArtist(@PathVariable Integer requestedId){
        Optional<Artists> optArtist = artistRepository.findById(requestedId);
        if(optArtist.isPresent()){
            Artists artist = optArtist.get();
            return ResponseEntity.ok(artist);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get")
    private ResponseEntity<Iterable<Artists>> getAll(){
        Iterable<Artists> artists = artistRepository.findAll();
        return ResponseEntity.ok(artists);
    }

    @PostMapping("/create")
    private ResponseEntity<Void> createArtist(@RequestBody Artists newArtist, UriComponentsBuilder ucb){
        Artists artist = artistRepository.save(newArtist);
        URI locationOfNewArtist = ucb.path("/get/{id}").buildAndExpand(newArtist.getId()).toUri();
        return ResponseEntity.created(locationOfNewArtist).build();
    }

    @PutMapping("/update")
    private ResponseEntity<Void> updateArtist(@PathVariable Integer id, @RequestBody Artists updateArtist){
        Optional<Artists> optArtist = artistRepository.findById(id);
        if(optArtist.isPresent()){
            Artists artist = optArtist.get();
            artistRepository.save(artist);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
