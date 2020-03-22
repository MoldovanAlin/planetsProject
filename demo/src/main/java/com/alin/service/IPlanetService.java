package com.alin.service;

import com.alin.model.Planet;

import java.util.List;

public interface IPlanetService {

    List<Planet> findAll();

    List<Planet> findAllById(long id);

    void deleteAll();

    void deleteById(Long id);

    boolean existsById(long id);

    void save(Planet planet);

    void updatePlanet(Planet planet);
}
