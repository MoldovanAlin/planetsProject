package com.alin.model;

import com.alin.utils.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Planet")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String imageURL;

    @Column(name = "status")
    private String status;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "captain", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Captain captain;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "robots", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Robots> robots = new ArrayList<Robots>();

    public Planet(){

    }

    public Planet(String name, String imageURL, String status, Captain captain, List<Robots> robots) throws IllegalAccessException {
        this.name = name;
        this.imageURL = imageURL;
        if(Status.OK.getStatus().equals(status) || Status.NOK.getStatus().equals(status) || Status.EN_ROUTE.getStatus().equals(status) || Status.TODO.getStatus().equals(status)){
            this.status = status;
        } else {
            throw new IllegalAccessException("Status incorrect");

        }
        this.captain = captain;
        this.robots = robots;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Captain getCaptain() {
        return captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }

    public List<Robots> getRobots() {
        return robots;
    }

    public void setRobots(List<Robots> robots) {
        this.robots = robots;
    }
}


