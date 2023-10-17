package com.example.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public ResponseEntity<List<Test>> groups() {
        List<Test> query = groupRepository.findAll();
        return new ResponseEntity<>(query, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createGroup(@RequestBody Test group) {
        groupRepository.save(group);
        return ResponseEntity.status(201).body("Group created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getGroup(@PathVariable Long id) {
        Optional<Test> query = groupRepository.findById(id);
        return new ResponseEntity<>(query.get(), HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Set<Person>> getGroupsPersons(@PathVariable Long id) {
        Optional<Test> query = groupRepository.findById(id);
        if (query.isPresent()) {
            Set<Person> persons = query.get().getPersons();
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
