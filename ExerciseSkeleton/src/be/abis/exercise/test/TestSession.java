package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.CourseParticipant;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.PublicSession;
import be.abis.exercise.repository.FilePersonRepository;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;
import java.io.IOException;
import java.time.LocalDate;

public class TestSession {

    Person sandy = new Person("Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10),"sschillebeeckx@abis.be","somepass1");

    PublicSession session = new PublicSession(Course.JAVA_ADVANCED,
            LocalDate.of(2022, 12, 4), PublicSession.ABIS, sandy);

    public void addParticipants() {
        session.addEnrolment((CourseParticipant) FilePersonRepository.getInstance().getPersons());
    }

    public static void main(String... args) {
        PublicSession session = new PublicSession(Course.JAVA_ADVANCED,
                LocalDate.of(2022, 12, 4), PublicSession.ABIS, PublicSession.getSandy());

        try {
            session.printListOfParticipants();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
