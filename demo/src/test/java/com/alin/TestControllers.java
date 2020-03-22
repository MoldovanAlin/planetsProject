package com.alin;


import com.alin.controller.PlanetController;
import com.alin.model.Captain;
import com.alin.model.Planet;
import com.alin.model.Robots;

import com.alin.service.IPlanetService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(MockitoJUnitRunner.class)
public class TestControllers {

    @InjectMocks
    private PlanetController planetController = new PlanetController();

    @Mock
    IPlanetService planetService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testCreatePlanet() throws Exception {
        long id = 1L;

        UriComponentsBuilder ucBuilder = mock(UriComponentsBuilder.class);
        when(ucBuilder.path("/planets/{id}")).thenReturn(ucBuilder);
        UriComponents uriComponents = mock(UriComponents.class);
        when(ucBuilder.buildAndExpand(id)).thenReturn(uriComponents);
        URI fake = new URI("fake");
        when(uriComponents.toUri()).thenReturn(fake);


        List<Planet> planetList = new ArrayList<Planet>();
        List<Robots> robotsList = new ArrayList<Robots>();
        Captain captain = new Captain("captain", "messagee");
        Robots robot = new Robots("robot");
        ;
        robotsList.add(robot);


        Planet planet = new Planet("terra", "www.google.com/image/terra", "OK", captain, robotsList);
        planet.setId(id);


        when(planetService.existsById(planet.getId())).thenReturn(false);

        ResponseEntity<Void> listResponseEntity = planetController.createPlanets(planet, ucBuilder);

        HttpHeaders headers = listResponseEntity.getHeaders();
        HttpStatus statusCode = listResponseEntity.getStatusCode();

        assertEquals(statusCode, HttpStatus.CREATED);
        verify(planetService).save(planet);
        assertEquals(headers.getLocation(), fake);
    }

    @Test
    public void testGetPlanet() throws Exception {
        long idToFind = 1L;


        List<Planet> planetList = new ArrayList<Planet>();
        List<Robots> robotsList = new ArrayList<Robots>();
        Captain captain = new Captain("captain", "messagee");
        Robots robot = new Robots("robot");
        ;
        robotsList.add(robot);


        Planet planet = new Planet("terra", "www.google.com/image/terra", "OK", captain, robotsList);

        planet.setId(idToFind);

        when(planetService.findAllById(idToFind)).thenReturn(Collections.singletonList(planet));

        ResponseEntity<List<Planet>> listResponseEntity = planetController.findAllPlanetsById(idToFind);
        HttpStatus statusCode = listResponseEntity.getStatusCode();
        List<Planet> planetFound = listResponseEntity.getBody();

        assertEquals(planetFound.get(0), planet);
        assertEquals(statusCode, HttpStatus.OK);
    }

    @Test
    public void testDeleteAllPlanets() throws Exception {

        ResponseEntity<Planet> listResponseEntity = planetController.deleteAllPlanets();
        HttpStatus statusCode = listResponseEntity.getStatusCode();
        Planet deletePlanet = listResponseEntity.getBody();

        verify(planetService).deleteAll();
        assertNull(deletePlanet);
        assertEquals(statusCode, HttpStatus.NO_CONTENT);
    }

    @Test
    public void testDeletePlanetById() throws Exception {
        long idToFind = 1L;

        when(planetService.findAllById(idToFind)).thenReturn(null);

        ResponseEntity<Planet> listResponseEntity = planetController.deletePlanetsById(idToFind);
        HttpStatus statusCode = listResponseEntity.getStatusCode();
        Planet deletePlanet = listResponseEntity.getBody();

        verify(planetService, times(0)).deleteById(idToFind);
        assertEquals(deletePlanet, null);
        assertEquals(statusCode, HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdatePlanet() throws Exception {
        long idToFind = 1L;


        List<Robots> robotsList = new ArrayList<Robots>();
        List<Robots> robotsList22 = new ArrayList<Robots>();
        Captain captain = new Captain("captain", "messagee");
        Captain captain1 = new Captain("captain1", "otherMessage");
        Robots robot = new Robots("robot");
        Robots robot22 = new Robots("robot22");
        robotsList.add(robot);
        robotsList22.add(robot22);


        Planet existingPlanet = new Planet("terra", "www.google.com/image/terra", "OK", captain, robotsList);

        when(planetService.findAllById(idToFind)).thenReturn(Collections.singletonList(existingPlanet));

        Planet planet1 = new Planet();
        planet1.setImageURL("https://openclipart.org/download/133291/miso-soup.svg");
        planet1.setName("marte");
        planet1.setStatus("!OK");
        planet1.setCaptain(captain1);
        planet1.setRobots(robotsList22);

        ResponseEntity<Planet> listResponseEntity = planetController.updatePlanets(idToFind, planet1);
        HttpStatus statusCode = listResponseEntity.getStatusCode();

        verify(planetService).updatePlanet(existingPlanet);
        assertEquals(existingPlanet.getName(), "marte");
        assertEquals(existingPlanet.getImageURL(), "https://openclipart.org/download/133291/miso-soup.svg");
        assertEquals(existingPlanet.getStatus(), "!OK");
        assertEquals(existingPlanet.getCaptain().getName(), captain1.getName());
        assertEquals(existingPlanet.getRobots(), robotsList22);


        assertEquals(statusCode, HttpStatus.OK);
    }
}
