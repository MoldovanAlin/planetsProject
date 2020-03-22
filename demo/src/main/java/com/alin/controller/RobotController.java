package com.alin.controller;


import com.alin.model.Robots;
import com.alin.service.IRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:63342", maxAge = 3600)
@RestController
public class RobotController {

    @Autowired
    private IRobotService robotService;

    @RequestMapping(value = "/robot", method = RequestMethod.POST)
    public ResponseEntity<Void> createRobot(@RequestBody Robots robot, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating robot " + robot.getName());

        robotService.save(robot);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/robot/{id}").buildAndExpand(robot.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
