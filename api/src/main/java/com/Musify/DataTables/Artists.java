package com.Musify.DataTables;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Artists {
    @Id
    @SequenceGenerator(
            name = "artists_id_sequence",
            sequenceName = "artists_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "artists_id_sequence"
    )
    private Integer Id;
    private String firstName;
    private String lastName;
    private String stageName;
    private String bandName;
    private Date birthday;
    private String location;
    private String activePeriod;
    private boolean type; //person or band, true if person

    public Artists(int id, String firstName, String lastName, String stageName, String bandName, Date birthday, String location, String activePeriod, boolean type) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.bandName = bandName;
        this.birthday = birthday;
        this.location = location;
        this.activePeriod = activePeriod;
        this.type = type;
    }



    public Artists() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActivePeriod() {
        return activePeriod;
    }

    public void setActivePeriod(String activePeriod) {
        this.activePeriod = activePeriod;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
