package be.abis.exercise.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingMain {

    public static void main(String... args) {

        Logger log = LogManager.getLogger("exceptionLogger");
        log.error("Logging error");



    }

}
