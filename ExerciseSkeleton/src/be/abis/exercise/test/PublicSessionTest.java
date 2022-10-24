package be.abis.exercise.test;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.PublicSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PublicSessionTest {

    Person instructor = new Person("Sandy", "Schillebeeck");
    LocalDate today = LocalDate.now();
    PublicSession session = new PublicSession(Course.JAVA_ADVANCED, today, new Company(), instructor);

    @Test
    void calculateRevenue() {
        assertEquals("€948,00", session.calculateRevenue(Course.JAVA_ADVANCED));
    }
}