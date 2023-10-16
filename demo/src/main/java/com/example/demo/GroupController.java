package com.example.demo;

import java.util.*;

import javax.swing.GroupLayout.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {
    
    @Autowired
    private GroupController GroupRepository;

    @Autowired
    private PersonRepository PersonRepository;

    @GetMapping
    public ResponseEntity<List<Group>> group() {
        List<Group> groups = GroupRepository.findAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroups(@PathVariable Long id) {
        Optional<Group> query = GroupRepository.findById(id);
        return new ResponseEntity<>(query.get(), HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<List<Group>> getPersonGroups(@PathVariable Long id) {
        Optional<Person> query = PersonRepository.findById(id);
        if (query.isPresent()) {
            return new ResponseEntity<>(query.get().getGroups(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
