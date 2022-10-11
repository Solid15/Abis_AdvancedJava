package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;

import java.io.IOException;
import java.util.List;

public interface PersonRepository {

    List<Person> getAllPersons();                      // TODO remove?
    void printPersonsSortedToFile(String file);         // TODO remove?
    void writeAllPersonsToFile() throws IOException;

    Person findPersonById(int id) throws PersonNotFoundException;
    Person findPerson(String email, String password) throws PersonNotFoundException;



}
