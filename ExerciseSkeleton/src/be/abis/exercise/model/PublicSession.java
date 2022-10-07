package be.abis.exercise.model;


import be.abis.exercise.exception.InvoiceException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class PublicSession extends Session {

	public final static Company ABIS = new Company("Abis");
	private ArrayList<CourseParticipant> enrolments = new ArrayList<CourseParticipant>();

	private static int counter = 0;
	private int sessionNumber;

	private static Person sandy = new Person("Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10),"sschillebeeckx@abis.be","somepass1");
				// TODO solve this differently ?


	public PublicSession(Course course, LocalDate date, Company location,
			Instructor instructor) {
		super(course, date, location, instructor);
		sessionNumber = ++counter;
	}

	@Override
	public double invoice() throws InvoiceException {
		System.out.println("Invoice in PublicSession");
		return 500;
	}

	public void addEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			this.addEnrolment(c);
	}

	protected void addEnrolment(CourseParticipant cp) {
		if (!enrolments.contains(cp)) {
			enrolments.add(cp);
			System.out.println("Enrolment added to the list, now "
					+ enrolments.size() + " enrolments.");
			System.out.println("enrollee is: " + cp);
		} else {
			System.out.println("Couldn't add " + cp + " as enrollee, since he was already enrolled");
		}
	}

	public void removeEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			removeEnrolment(c);
	}

	protected void removeEnrolment(CourseParticipant cp) {
		if (enrolments.contains(cp)) {
			enrolments.remove(cp);
			System.out.println("Enrollee " + cp + " removed from the list, now "
					+ enrolments.size() + " enrolments.");
		} else {
			System.out.println("Couldn't remove enrolment.");
		}
	}

	public void printListOfParticipants() throws IOException {
		PrintWriter printWriter =
				new PrintWriter(new FileWriter("/temp/javacourses/participants-"+sessionNumber+".txt"));

		System.out.printf("%20s", "UNIX/Linux shell programming");
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.printf("%1$-2s %2$-10s %4$1s %3$1s", "Instructor", sandy.getFirstName(), sandy.getLastName(), " ");
	//	System.out.printf("%1-2s$%2$-10s%3$1s%4$%5$", "Location", ABIS.getName(), ABIS.getAddress().getStreet(),
//				ABIS.getAddress().getNr(), ABIS.getAddress().getZipCode(), ABIS.getAddress().getTown());
		// TODO
	}

	public String calculateRevenue(Course course) {

		String revenueValue = ((Double) (course.getDailyPrice() * course.getDays())).toString();
		BigDecimal revenueTotal = new BigDecimal(revenueValue);
		revenueTotal = revenueTotal.divide(new BigDecimal("100"), RoundingMode.HALF_EVEN)
												.multiply(new BigDecimal("79"));
		NumberFormat euroFormat = NumberFormat.getCurrencyInstance(new Locale("nl","BE"));
		euroFormat.setGroupingUsed(false);  // a thousand separator
		return euroFormat.format(revenueTotal).replaceFirst("\\u00a0", ""); 	// not a regular empty space
	}
	
	public Iterator<CourseParticipant> getEnrolmentsIterator(){
		return enrolments.iterator();
	}

	@Override
	public Company getOrganizer() {
		return ABIS;
	}

	public ArrayList<CourseParticipant> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(ArrayList<CourseParticipant> enrolments) {
		this.enrolments = enrolments;
	}



	public static int getCounter() {
		return counter;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}


	public static Person getSandy() {
		return sandy;
	}
}