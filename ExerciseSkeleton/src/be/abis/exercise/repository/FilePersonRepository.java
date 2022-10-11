package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilePersonRepository implements PersonRepository {

    private static  FilePersonRepository personRepository;
    private static final String FILELOCATION =
"C:\\Users\\Duser\\IdeaProjects\\advancedjava\\ExerciseSkeleton\\src\\be\\abis\\exercise\\resources\\persons.csv";
    private List<Person> people = new ArrayList<>();

    private FilePersonRepository() {

        Address a1 = new Address("Diestsevest","32/4b","3000","Leuven","België","BE");
        Address a2 = new Address("Sint-Lazaruslaan","10","1210","Saint-Josse-Ten-Noode","Belgique","BE");
        Address a3 = new Address("Avenue du Bourget","42","1130","Brussels","Belgium","BE");
        Address a4 = new Address("Amsterdamseweg","55","1182GP","Amstelveen","Nederland","NL");

        Company c1 = new Company("ABIS",a1);
        Company c2 = new Company("BNP Paribas Fortis",a2);
        Company c3 = new Company("IBM",a3);
        Company c4 = new Company("KLM",a4);
        Company c5 = new Company("ABC Anonymous");

        Person inst1 = new Person("Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10),"sschillebeeckx@abis.be","somepass1", c1);
        Person inst2 = new Person("Koen", "De Backer",LocalDate.of(1962, 11, 25),"kdebacker@abis.be","somepass2", c1);
        Person inst3 = new Person("Gie", "Indesteege",LocalDate.of(1958, 8, 19),"gindesteege@abis.be","somepass3", c1);
        Person inst4 = new Person("Bart", "Lemarcq",LocalDate.of(1976, 2, 12),"blemarcq@abis.be","somepass4", c1);
        Person p1 = new Person("Michel","Dupont",LocalDate.of(1980, 5, 5),"michel.dupont@bnpparibasfortis.com","somepass5",c2);
        Person p2 = new Person("Anne","Van der Meulen",LocalDate.of(1984, 9, 30),"anne.vandermeulen@bnpparibasfortis.com","somepass6",c2);
        Person p3 = new Person("Bob","Miles",LocalDate.of(1967, 3, 11),"bob.miles@ibm.com","somepass7",c3);
        Person p4 = new Person("Willem-Alexander","Janssen",LocalDate.of(1971, 1, 18),"willemalexander.janssen@klm.nl","somepass8",c4);
        Person p5 = new Person("Jef","Smits",LocalDate.of(1988, 10, 10),"jefke@yahoo.com","somepass9");

        people.addAll(Arrays.asList(inst1,inst2,inst3,inst4,p1,p2,p3,p4,p5));
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
    public List<Person> getAllPersons() {
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

    @Override                           // TODO FileReader
    public Person findPersonById(int id) throws PersonNotFoundException {
      return people.stream().filter(person -> person.getPersonNumber() == id).findFirst()
              .orElseThrow( ()-> new PersonNotFoundException("Person not found."));   // supplier lambda
    }

    @Override
    public Person findPerson(String email, String password) throws PersonNotFoundException {
        return people.stream().filter(person -> person.getEmail().equals(email))
                    .filter(person -> person.getPassword().equals(password))
                    .findFirst().orElseThrow( ()-> new PersonNotFoundException("Person not found."));
    }


    public static FilePersonRepository getInstance() {
        if (personRepository == null) personRepository = new FilePersonRepository();
        return personRepository;
    }

}
