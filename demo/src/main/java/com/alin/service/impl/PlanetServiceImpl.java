package com.alin.service.impl;


import com.alin.model.Planet;
import com.alin.repo.PlanetRepository;
import com.alin.service.IPlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service("planetService")
public class PlanetServiceImpl implements IPlanetService {
    @Autowired
    private PlanetRepository repository;

    @Transactional
    public List<Planet> findAll() {

        return (List<Planet>) repository.findAll();
    }

    @Transactional
    public List<Planet> findAllById(long id) {
        return (List<Planet>) repository.findAllById(Collections.singleton(id));
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();

    }

    @Transactional
    public void delete(Planet planet) {
        repository.delete(planet);

    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Transactional
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    @Transactional
    public void save(Planet planet) {
        repository.save(planet);
    }

    @Transactional
    public void updatePlanet(Planet planet) {
        repository.updatePlanet(planet);
    }
}
