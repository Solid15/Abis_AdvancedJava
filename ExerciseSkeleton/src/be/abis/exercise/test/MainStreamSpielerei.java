package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MainStreamSpielerei {

    public static void main(String... args) {

       Logger log = LogManager.getLogger("exceptionLogger");

       List<Person> people = new ArrayList<>(FilePersonRepository.getInstance().getAllPersons());
       people.stream().filter(person -> person.getCompany() != null)
               .filter(person -> person.getCompany().getName().equalsIgnoreCase("ABIS"))
               .forEach(System.out::println);          //  (person -> System.out.println(person));

        System.out.println();
        people.stream().filter(person -> person.getLastName().startsWith("S"))
                .sorted(Comparator.comparing(Person::getFirstName))
      //          .map(Person::getLastName).forEach(name -> System.out.println(name.toUpperCase()));
              .forEach( p -> System.out.println(p.getFirstName() + " " + p.getLastName().toUpperCase()));

        System.out.println();
        people.stream().filter(person -> person.getCompany() != null)
                .map(person -> person.getCompany().getName()).distinct().forEach(System.out::println);

        System.out.println();
        long count = people.stream().filter(person -> person.getCompany() != null)
                        .filter(person -> person.getCompany().getAddress().getTown()
                        .equalsIgnoreCase("Leuven")).count();
        System.out.println("Total number of people working in Leuven: " + count);

        try {
            Person youngest = people.stream().filter(person -> person.getBirthDate() != null).min
                    (Comparator.comparing(person -> LocalDate.now().getYear() - person.getBirthDate().getYear()))
                    .orElseThrow( ()-> new PersonNotFoundException("Person not found."));
            System.out.println("The youngest person is " + youngest.getFirstName() + " " + youngest.getLastName());
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Person firstElderly = people.stream()
                    .filter(person -> person.getBirthDate() != null)
       //           .filter(person -> (LocalDate.now().getYear() - person.getBirthDate().getYear()) > 50)
                    .filter(person -> person.calculateAge() > 50)
                    .findFirst()
                    .orElseThrow( ()-> new PersonNotFoundException("Person not found."));
            System.out.println("First person with age > 50: " + firstElderly.getFirstName());
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }
/*
        System.out.println();                               //  alternative to 50y does not work
        System.out.println(
                ((Person) personStream.stream().filter(p -> (LocalDate.now().getYear() - p.getBirthDate().getYear()) > 50)
                .limit(1)).getFirstName()  );       //  ClassCast Exception
*/

        Map<String, List<Person>> groupedByCompany =
                people.stream().filter(person -> person.getCompany() != null)
                .collect(Collectors.groupingBy(person -> person.getCompany().getName()));
        System.out.println(groupedByCompany);

        Map<Object, Long> numberByCompany =
                people.stream().filter(person -> person.getCompany() != null)
                        .collect(Collectors.groupingBy(person -> person.getCompany().getName(), Collectors.counting()));
        System.out.println(numberByCompany);

        for (Person person: people) {
            System.out.println(person.getFirstName() + " is " + person.calculateAge() + " years old.");
        }

        BigDecimal averageAge =
                 BigDecimal.valueOf(people.stream().filter(person -> person.getBirthDate() != null)
                .mapToInt(Person::calculateAge)
                .average()
        //      .orElse()
        //      .orElseThrow()
         //     .isPresent()            // boolean
                .getAsDouble());
        System.out.println("The average age is: " + averageAge.setScale(0, RoundingMode.HALF_UP) + ".");

        System.out.println("Number of people in List: " + people.size());
        people.removeIf(person -> person.getCompany() == null);             // remove works on List, not Stream
        System.out.println("Number of people in List: " + people.size());
 //       people.forEach(System.out::println);


                                            // TODO convertStringToPerson()
                                            // TODO convertPersonToString()




    }
}
