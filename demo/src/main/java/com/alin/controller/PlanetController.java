package com.alin.controller;


import com.alin.model.Planet;
import com.alin.service.IPlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342", maxAge = 3600)
@RestController
public class PlanetController {

    @Autowired
    private IPlanetService planetService;


    @RequestMapping(value = "/planets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Planet>> findAllPlanets() {

        List<Planet> planets = planetService.findAll();
        if (planets == null) {
            System.out.println("planets not found");
            return new ResponseEntity<List<Planet>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Planet>>(planets, HttpStatus.OK);
    }

    @RequestMapping(value = "/planets/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Planet>> findAllPlanetsById(@PathVariable("id") long id) {

        List<Planet> planets = planetService.findAllById(id);
        if (planets == null) {
            System.out.println("planets not found");
            return new ResponseEntity<List<Planet>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Planet>>(planets, HttpStatus.OK);
    }

    @RequestMapping(value = "/planets", method = RequestMethod.POST)
    public ResponseEntity<Void> createPlanets(@RequestBody Planet planet, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Planet " + planet.getName());

        if (planetService.existsById(planet.getId())) {
            System.out.println("A Planet with id " + planet.getId() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        planetService.save(planet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/planets/{id}").buildAndExpand(planet.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deletePlanets/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Planet> deletePlanetsById(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Planet with id " + id);

        List<Planet> planets = planetService.findAllById(id);
        if (planets == null) {
            System.out.println("Unable to delete. Planet with id " + id + " not found");
            return new ResponseEntity<Planet>(HttpStatus.NOT_FOUND);
        }

        planetService.deleteById(id);
        return new ResponseEntity<Planet>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/planets", method = RequestMethod.DELETE)
    public ResponseEntity<Planet> deleteAllPlanets() {
        System.out.println("Deleting All Planets");

        planetService.deleteAll();
        return new ResponseEntity<Planet>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/updatePlanet/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Planet> updatePlanets(@PathVariable("id") long id, @RequestBody Planet planet) {
        System.out.println("Updating Planet " + id);

        List<Planet> currentPlanet = planetService.findAllById(id);

        if (currentPlanet == null) {
            System.out.println("Planet with id " + id + " not found");
            return new ResponseEntity<Planet>(HttpStatus.NOT_FOUND);
        }


        currentPlanet.get(0).setName(planet.getName());
        currentPlanet.get(0).setImageURL(planet.getImageURL());
        currentPlanet.get(0).setStatus(planet.getStatus());
        currentPlanet.get(0).setCaptain(planet.getCaptain());
        currentPlanet.get(0).setRobots(planet.getRobots());


        planetService.updatePlanet(currentPlanet.get(0));
        return new ResponseEntity<Planet>(currentPlanet.get(0), HttpStatus.OK);
    }


}
