package com.Musify.DataTables;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Songs {
    @Id
    @SequenceGenerator(
            name = "songs_id_sequence",
            sequenceName = "songs_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "songs_id_sequence"
    )
    private Integer id;
    private String title;
    private String alternativeTitles;
    private double duration;
    private Date creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artists_id")
    private Artists artists;

    public Songs(Integer id, String title, String alternativeTitles, double duration, Date creationDate, Artists artists) {
        this.id = id;
        this.title = title;
        this.alternativeTitles = alternativeTitles;
        this.duration = duration;
        this.creationDate = creationDate;
        this.artists = artists;
    }

    public Songs() {
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

    public String getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(String alternativeTitles) {
        this.alternativeTitles = alternativeTitles;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }
}
