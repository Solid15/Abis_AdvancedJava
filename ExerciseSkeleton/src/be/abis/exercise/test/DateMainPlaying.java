package be.abis.exercise.test;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.PublicSession;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;

public class DateMainPlaying {

    public static void main(String... args) {

        LocalDate myBirthDay = LocalDate.of(1984,2,23);
        LocalDate nextAnniversary = LocalDate.of(2023, 2, 23);
        LocalDate today = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime now = LocalDateTime.now();
        TimeZone hereTime = TimeZone.getDefault();
        TimeZone kolkotaTime = TimeZone.getTimeZone("Asia/Kolkata");

        System.out.println(LocalDate.now().plusYears(3).plusMonths(2).plusDays(15));
        System.out.println(LocalDate.of(1984, 2, 23).getDayOfWeek()); //.toString().toLowerCase());
        System.out.println(LocalDate.of(2023, 2, 23));
        System.out.println(ChronoUnit.DAYS.between(today, nextAnniversary));        // TODO ChronoUnit
        System.out.println(ChronoUnit.DAYS.between(myBirthDay, today));
        System.out.println(today.compareTo(myBirthDay));   // result in years
        System.out.println(ZonedDateTime.now().getOffset());            // Greenwich Difference

        ZonedDateTime todayNow = ZonedDateTime.now();
        ZonedDateTime kolkata  = ZonedDateTime.of(now, kolkotaTime.toZoneId());
        System.out.println(Duration.between(kolkata, todayNow));
        System.out.println(kolkata.getOffset());                        // Kolkata Greenwich Difference

        System.out.println();
        System.out.println(TimeZone.getDefault());
        System.out.println(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println(Locale.getDefault());
        System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));
        System.out.println(Arrays.toString(Locale.getISOCountries()));
        System.out.println(Arrays.toString(Locale.getAvailableLocales()));
        System.out.println(Arrays.toString(Locale.getISOLanguages()));
        System.out.println(today.atStartOfDay(kolkotaTime.toZoneId()));
        System.out.println(today.until(nextAnniversary).getDays());             // same as below
        System.out.println(Period.between(today, nextAnniversary).getDays());  // only days, not months/years

        Person instructor = new Person("Sandy", "Schillebeeckx");
        PublicSession session = new PublicSession(Course.JAVA_ADVANCED, today, new Company("ABIS"), instructor);
        System.out.println(session.calculateRevenue(Course.JAVA_ADVANCED));

        System.out.println();
        System.out.println(session.toString(new Locale("nl")));
        System.out.println(session.toString(new Locale("fr")));


    }
}
