package com.alin.controller;


import com.alin.model.Captain;
import com.alin.service.ICaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:63342", maxAge = 3600)
@RestController
public class CaptainController {

    @Autowired
    private ICaptainService captainService;

    @RequestMapping(value = "/captain", method = RequestMethod.POST)
    public ResponseEntity<Void> createCaptain(@RequestBody Captain captain, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating captain " + captain.getName());

        captainService.save(captain);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/captain/{id}").buildAndExpand(captain.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
