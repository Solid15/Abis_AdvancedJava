package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilePersonRepositoryBySandy implements PersonRepository {

    private List<Person> allPersons = new ArrayList<Person>();
    private static final String FILELOCATION = "/temp/javacourses/persons.csv";

    public FilePersonRepositoryBySandy()  {
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

        allPersons.addAll(Arrays.asList(inst1,inst2,inst3,inst4,p1,p2,p3,p4,p5));

    }

    @Override
    public void writeAllPersonsToFile() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(FILELOCATION));
        for (Person pe : allPersons) {
            StringBuilder sb = this.convertPersonToString(pe);
            pw.println(sb);
        }
        pw.close();
    }

    @Override
    public Person findPersonById(int id) throws PersonNotFoundException {
        return null;
    }

    @Override
    public Person findPerson(String email, String password) throws PersonNotFoundException {
        return null;
    }

    private StringBuilder convertPersonToString(Person p) {
        Company c=p.getCompany();
        StringBuilder sb = new StringBuilder();
        sb.append(p.getPersonNumber()).append(";")
                .append(p.getFirstName()).append(";")
                .append(p.getLastName()).append(";")
                .append(p.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(";")
                .append(p.getEmail()).append(";")
                .append(p.getPassword()).append(";")
                .append(c!=null?c.getName():"null").append(";")
                .append(c!=null?c.getAddress().getStreet():"null").append(";")
                .append(c!=null?c.getAddress().getNr():"null").append(";")
                .append(c!=null?c.getAddress().getZipCode():"null").append(";")
                .append(c!=null?c.getAddress().getTown():"null").append(";")
                .append(c!=null?c.getAddress().getCountry():"null").append(";")
                .append(c!=null?c.getAddress().getCountryCode():"null").append(";");
        return sb;
    }

    @Override
    public List<Person> getAllPersons() {
        return allPersons;
    }

    public void setAllPersons(List<Person> allPersons) {
        this.allPersons = allPersons;
    }
    public static String getFilelocation(){
        return FILELOCATION;
    }


    @Override
    public void printPersonsSortedToFile(String file) {

    }
}
