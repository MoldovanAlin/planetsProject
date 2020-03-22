package com.alin.service.impl;


import com.alin.model.Robots;
import com.alin.repo.RobotRepository;
import com.alin.service.IRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("robotService")
public class RobotServiceImpl implements IRobotService {
    @Autowired
    private RobotRepository repository;


    @Transactional
    public void save(Robots robot) {
        repository.save(robot);
    }


}
