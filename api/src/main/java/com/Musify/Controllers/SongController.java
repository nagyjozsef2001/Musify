package com.Musify.Controllers;

import com.Musify.DataRepositories.SongRepository;
import com.Musify.DataTables.Songs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GetMapping("/get/{requestedId}")
    private ResponseEntity<Songs> getSong (@PathVariable Integer requestedId){
        Optional<Songs> optSong = songRepository.findById(requestedId);
        if(optSong.isPresent()){
            Songs song = optSong.get();
            return ResponseEntity.ok(song);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    private ResponseEntity<Void> createSong(@RequestBody Songs newSong, UriComponentsBuilder ucb){
        Songs song = songRepository.save(newSong);
        URI locationOfNewSong = ucb.path("/get/{Id}").buildAndExpand(song.getId()).toUri();
        return ResponseEntity.created(locationOfNewSong).build();
    }

    @PutMapping("/update/{requestedId}")
    private ResponseEntity<Void> updateSong(@PathVariable Integer requestedId, @RequestBody Songs updateSong){
        Optional<Songs> optSong = songRepository.findById(requestedId);
        if (optSong.isPresent()){
            Songs song = optSong.get();
            song.setTitle(updateSong.getTitle());
            song.setAlternativeTitles(updateSong.getAlternativeTitles());
            song.setDuration(updateSong.getDuration());
            song.setArtists(updateSong.getArtists());
            song.setCreationDate(updateSong.getCreationDate());
            songRepository.save(song);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
