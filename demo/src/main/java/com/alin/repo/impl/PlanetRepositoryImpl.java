package com.alin.repo.impl;

import com.alin.model.Planet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public class PlanetRepositoryImpl {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void updatePlanet(@PathVariable Planet planet) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("update planet set name= :name,imageURL = :imageURL, status = :status , where id = :id");
        query.setParameter("id", Long.valueOf(planet.getId()));
        query.setParameter("name", planet.getName());
        query.setParameter("imageURL", planet.getImageURL());
        query.setParameter("status", planet.getStatus());


        query.executeUpdate();
        session.close();
    }
}
