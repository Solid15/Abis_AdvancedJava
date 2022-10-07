package be.abis.exercise.model;

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