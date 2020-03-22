package com.alin.service.impl;


import com.alin.model.Captain;
import com.alin.repo.CaptainRepository;
import com.alin.service.ICaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("captainService")
public class CaptainServiceImpl implements ICaptainService {
    @Autowired
    private CaptainRepository repository;


    @Transactional
    public void save(Captain captain) {
        repository.save(captain);
    }


}
