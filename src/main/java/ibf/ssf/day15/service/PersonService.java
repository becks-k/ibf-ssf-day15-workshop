package ibf.ssf.day15.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf.ssf.day15.model.Person;
import ibf.ssf.day15.repository.PersonRepo;

@Service
public class PersonService {
    
    @Autowired
    PersonRepo personRepo;

    
    public void addPerson(String key, Person person) {
        personRepo.addToList(key, person.toString());
    }
    
    public List<Person> getPersonList(String key) {
        List<String> rawList = personRepo.getList(key);
        List<Person> persons = rawList.stream()
            .map(p -> {
                String[] record = p.split(",");
                Person person = new Person();
                person.setId(Integer.parseInt(record[0]));
                person.setFullName(record[1]);
                person.setSalary(Integer.parseInt(record[2]));
                return person;
            })
            .collect(Collectors.toList());
        
        // Cast List<String> of person to List<Person>
        // Rewrite this in stream RECODE
        // for (String raw : rawList) {
        //     String [] record = raw.split(",");
        //     Person person = new Person();
        //     person.setId(Integer.parseInt(record[0])); // During validation stage to ensure that only numbers are accepted
        //     person.setFullName(record[1]);
        //     person.setSalary(Integer.parseInt(record[2]));
        //     persons.add(person);
        // }

        return persons;
    }

    public Boolean deletePerson(String key, Person person) {
        Boolean isDeleted = personRepo.removeFromList(key, person.toString());
        return isDeleted;
    }
}
