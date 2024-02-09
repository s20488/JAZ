package com.example.services;

import com.example.contracts.EmploymentDto;
import com.example.contracts.PersonDto;
import com.example.model.Employment;
import com.example.model.Person;
import com.example.repositories.EmploymentRepository;
import com.example.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final EmploymentRepository employmentRepository;

    public PersonService(PersonRepository personRepository, EmploymentRepository employmentRepository) {
        this.personRepository = personRepository;
        this.employmentRepository = employmentRepository;
    }

    public List<PersonDto> getAll(){
        List<PersonDto> personList = new ArrayList<>();
        for(Person p: personRepository.findAll()){
            personList.add(new PersonDto(p.getId(), p.getFirstName(), p.getLastName()));
        }
        return personList;
    }

    public Optional<Person> getById(int id){
        return personRepository.findById(id);
    }

    public void save(PersonDto personDto){
        Person person = new Person();
        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirst_Name());
        person.setLastName(personDto.getLast_Name());
        personRepository.save(person);
    }

    public void update(int personId, PersonDto personDto){
        Optional<Person> p = personRepository.findById(personId);
        if(p.isPresent()){
            Person person = p.get();
            person.setFirstName(personDto.getFirst_Name());
            person.setLastName(personDto.getLast_Name());
            personRepository.save(person);
        }
    }

    public void delete(int personId){
        personRepository.deleteById(personId);
    }

    public void addEmployment(int personId, EmploymentDto employmentDto){
        Optional<Person> p = personRepository.findById(personId);
        if(p.isPresent()){
            Person person = p.get();
            var employment = new Employment();
            employment.setId(employmentDto.getId());
            employment.setTitle(employmentDto.getTitle());
            employment.setKeySkills(employmentDto.getKey_skills());
            employment.setPerson(person);
            person.getEmployment().add(employment);
            personRepository.save(person);
            employmentRepository.save(employment);
        }
    }

    public List<EmploymentDto> getEmployments(int personId){
        List<EmploymentDto> employments = new ArrayList<>();
        Optional<Person> p = personRepository.findById(personId);
        if(p.isPresent()){
            Person person = p.get();
            var employment = person.getEmployment();
            for(Employment e: employment){
                employments.add(new EmploymentDto(e.getId(),
                        e.getTitle(),
                        e.getKeySkills()));
            }
        }
        return employments;
    }
}
