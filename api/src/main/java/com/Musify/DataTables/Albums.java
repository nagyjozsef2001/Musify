package com.Musify.DataTables;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Albums {
    @Id
    @SequenceGenerator(
            name = "albums_id_sequence",
            sequenceName = "albums_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "albums_id_sequence"
    )
    private Integer id;
    private String title;
    private String description;
    private String artist;
    private Date releaseDate;
    private String label;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "artists_id")
    private Artists artists;

    public Albums(Integer id, String title, String description, String artist, Date releaseDate, String label, Artists artists) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.label = label;
        this.artists = artists;
    }

    public Albums() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }


}
