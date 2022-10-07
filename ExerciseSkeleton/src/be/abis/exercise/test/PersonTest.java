package be.abis.exercise.test;

import be.abis.exercise.model.Person;
import org.junit.jupiter.api.Test;

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
}