package com.example.controllers;

import com.example.contracts.EmploymentDto;
import com.example.contracts.PersonDto;
import com.example.model.Person;
import com.example.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable int id) {
        Optional<Person> person = personService.getById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void save(@RequestBody PersonDto personDto) {
        personService.save(personDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody PersonDto personDto) {
        try {
            personService.update(id, personDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try {
            personService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/employments")
    public ResponseEntity<Void> addEmployment(@PathVariable int id, @RequestBody EmploymentDto employmentDto) {
        try {
            personService.addEmployment(id, employmentDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/employments")
    public ResponseEntity<List<EmploymentDto>> getEmployments(@PathVariable int id) {
        try {
            List<EmploymentDto> employments = personService.getEmployments(id);
            return ResponseEntity.ok(employments);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
