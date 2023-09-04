package com.Musify.DataTables;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Playlists {
    @Id
    @SequenceGenerator(
            name = "playlists_id_sequence",
            sequenceName = "playlists_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "playlists_id_sequence"
    )
    private Integer Id;
    private String name;
    private boolean type;//private or public, true if public
    private Date createdDate;
    private Date lastUpdatedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private Users users;

    public Playlists(Integer id, String name, boolean type, Date createdDate, Date lastUpdatedDate, Users users) {
        Id = id;
        this.name = name;
        this.type = type;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.users = users;
    }

    public Playlists() {
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
