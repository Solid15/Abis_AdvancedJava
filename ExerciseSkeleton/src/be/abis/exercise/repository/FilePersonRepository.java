package be.abis.exercise.repository;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilePersonRepository implements PersonRepository {

    private static  FilePersonRepository personRepository;
    private static final String FILELOCATION = "/temp/javacourses/persons.csv";
    private List<Person> people = new ArrayList<>();

    private FilePersonRepository() {
        people.add(new Person("Johny","Bass"));
        people.add(new Person("Marvelous", "Myrin"));
        people.add(new Person("Sounding", "Underground"));
        people.add(new Person("Michelangelo", "Pizza-Turtles"));

    }

    public FilePersonRepository(String filler) {            // String filler for alternative constructor
        try {
            //	List<String> compStrings = Files.readAllLines(Paths.get("c:\\temp\\javacourses\\companies.txt"));
            List<String> personLines = Files.readAllLines(Paths.get(FILELOCATION));
            people = new ArrayList<>();    // TODO  is this necessary?
            for (String person : personLines){
                String[] personData = person.split(";");
                if (!personData[5].equalsIgnoreCase("null") && !personData[2].equalsIgnoreCase("null")) {
                    people.add(new Person(personData[0], personData[1],
                            LocalDate.parse(personData[2], DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                            personData[3], personData[4],
                            new Company(personData[5], new Address(personData[6],
                            personData[7], personData[8], personData[9], personData[10], personData[11]))));
                } else if (!personData[5].equalsIgnoreCase("null") && personData[2].equalsIgnoreCase("null")) {
                    people.add(new Person(personData[0], personData[1],
                            new Company(personData[5], new Address(personData[6],
                            personData[7], personData[8], personData[9], personData[10], personData[11]))));
                } else if (personData[5].equalsIgnoreCase("null") && !personData[2].equalsIgnoreCase("null")) {
                        people.add(new Person(personData[0], personData[1],
                                LocalDate.parse(personData[2], DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                                personData[3], personData[4]));
                } else if (personData[5].equalsIgnoreCase("null") && personData[2].equalsIgnoreCase("null")) {
                    people.add(new Person(personData[0], personData[1]));
                }
                // TODO to work with setters and assigned variables for localDate/Company might be clearer
                // when writing persons -> "null" for all empty fields, so here every1 has same # strings

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }

    @Override
    public List<Person> getPersons() {
        return people;
    }

    @Override
    public void printPersonsSortedToFile(String file) {

    }

    @Override
    public void writeAllPersonsToFile() throws IOException  {
        Collections.sort(people);
        try (PrintWriter pw = new PrintWriter(FILELOCATION)){           // TODO rewrite entire write phase
            for (Person person : people){                       // TODO check birthdate formatter

                StringBuilder writtenLine = new StringBuilder(person.getFirstName()).append(";")
                        .append(person.getLastName()).append(";")
                        .append(person.getBirthDate().format(DateTimeFormatter.ofPattern(("dd/MM/yyyy"))))
                        .append(";").append(person.getEmail()).append(";").append(person.getPassword())
                        .append(";").append(person.getCompany().getName().toUpperCase()).append(";")
                        .append(person.getCompany().getAddress().getStreet()).append(";")
                        .append(person.getCompany().getAddress().getNr()).append(";")
                        .append(person.getCompany().getAddress().getZipCode()).append(";")
                        .append(person.getCompany().getAddress().getTown()).append(";")
                        .append(person.getCompany().getAddress().getCountry()).append(";")
                        .append(person.getCompany().getAddress().getCountryCode()).append(";");


                pw.println(person);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static FilePersonRepository getInstance() {
        if (personRepository == null) personRepository = new FilePersonRepository();
        return personRepository;
    }

}
