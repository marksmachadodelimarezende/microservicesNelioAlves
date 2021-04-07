package com.microservice.hrworker.resources;

import com.microservice.hrworker.entities.Worker;
import com.microservice.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/workers")
public class WorkerResouce {

    private static Logger logger = LoggerFactory.getLogger(WorkerResouce.class);

    @Autowired
    private Environment env;

    private WorkerRepository workerRepository;

    @Autowired
    public WorkerResouce(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        var response = workerRepository.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        logger.info("PORT: " + env.getProperty("local.server.port"));
        var response = workerRepository.findById(id).get();
        return ResponseEntity.ok(response);
    }
}
