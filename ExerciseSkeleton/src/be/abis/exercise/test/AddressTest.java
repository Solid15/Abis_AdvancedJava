package be.abis.exercise.test;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;
    private ZipCodeNotCorrectException zipCodeNotCorrectException;

    @BeforeEach
    public void setUp() {
        zipCodeNotCorrectException = new ZipCodeNotCorrectException("Not a ZipCode");
    }

    @AfterEach
    public void tearDown() {
        address = null;
        zipCodeNotCorrectException = null;
    }


    @Test
    void checkWrongBelgianZipCode() throws ZipCodeNotCorrectException {
        address = new Address("Biezestraat", "56", "9U17", "Gent", "België","BE");
        assertThrows(ZipCodeNotCorrectException.class, ()-> address.checkZipCode());
    }

    @Test
    void checkCorrectBelgianZipCode() throws ZipCodeNotCorrectException {
        address = new Address("Biezestraat", "56", "9000", "Gent", "België","BE");
        assertDoesNotThrow(() -> address.checkZipCode());
    }
}