package com.alin.repo;

import com.alin.model.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {
    void updatePlanet(Planet planet);

}
