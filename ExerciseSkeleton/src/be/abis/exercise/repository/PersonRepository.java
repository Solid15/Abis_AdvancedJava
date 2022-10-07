package be.abis.exercise.repository;

import be.abis.exercise.model.Person;

import java.io.IOException;
import java.util.List;

public interface PersonRepository {

    List<Person> getPersons();                      // TODO remove?
    void printPersonsSortedToFile(String file);         // TODO remove?
    void writeAllPersonsToFile() throws IOException;



}
