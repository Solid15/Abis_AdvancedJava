package be.abis.exercise.test;

import be.abis.exercise.model.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {


    @Test
    void testEqualsSameName() {
        Person a = new Person("Johny", "Mallone");
        Person b = new Person( "Johny", "Mallone");
        assertEquals(a, b);  // assertTrue(a.equals(b));
    }

    @Test
    void testEqualsDifferentName() {
        Person a = new Person("Johny", "Mallone");
        Person b = new Person( "Johny", "Walker");
        assertNotEquals(a, b);                        // assertFalse(a.equals(b));
    }

    @Test
    void testHashCode() {
        Person a = new Person("Johny", "Mallone");
        Person b = new Person( "Johny", "Mallone");
                // TODO HashSet to get access to HashCode ??
    }


    @Test
    void testReduceAgeOfPersonBy20Percent() {
        LocalDate date = LocalDate.of(1970,12, 12);
        Person person = new Person("Johny", "Walker", date,
                "someEmail@bond.com", "randomPasswordNumber2");
        double age = LocalDate.now().getYear() - person.getBirthDate().getYear();       // or use BiFunction
        Function<Double, Double> someFunction = d -> d * 4 / 5;
        assertEquals(41.6, someFunction.apply(age));
    }

}