package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTry {

	public static void main(final String[] args) throws ParseException {

		// final Scanner scan = new Scanner(System.in);
		// final String input = scan.nextLine();

		final String input = "fdgf 16:20 fddsg 15:20";

		final DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		final Date date = dateFormat.parse(input);

		System.out.println(date);
	}
}
