package main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * parking payment calculation
 *
 */
public class parkingPayment {

	// BUILDER pattern

	private final static String TIME_PATTERN = "\\b([01][0-9]|2[0-3])h[0-5][0-9]\\b";

	public static void main(final String[] args) {

		// final Scanner scan = new Scanner(System.in);
		// final String input = scan.nextLine();

		final String input = "2 voiture dsrdd 13h20 fddsg 16h30";
		final String[] wordsInInput = input.split("\\s+");

		final Pattern p = Pattern.compile(TIME_PATTERN, Pattern.CASE_INSENSITIVE);
		final Matcher match1 = p.matcher(input);

		final List<Date> dateList = new ArrayList<>();

		while (match1.find()) {
			final String time = match1.group().toLowerCase();

			final int hour = Integer.parseInt(time.split("h")[0]);
			final int minutes = Integer.parseInt(time.split("h")[1]);

			final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

			try {
				final Date date = sdf.parse(hour + ":" + minutes);

				System.out.println("Date cal is : " + date);
				dateList.add(date);
			} catch (final ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		final long diff = getTimeDifference(dateList);
		final int totalVehicle = Integer.parseInt(wordsInInput[0]);
		final String vehicle = wordsInInput[1];
		final String vehicleType = wordsInInput[2];

		final BigDecimal amount = getAmount(totalVehicle, vehicle, vehicleType, diff);
		System.out.println("End result : " + amount);

	}

	private static BigDecimal getAmount(final int totalVehicle, final String vehicle, final String vehicleType,
			final long diff) {
		// TODO Auto-generated method stub

		BigDecimal totalAmount = BigDecimal.ZERO;
		final int extraCharge = Objects.equals(vehicleType, "GPL") ? 0 : 7;

		if (Objects.equals(vehicle, "voiture")) {
			totalAmount = calculateAmount(totalVehicle, extraCharge, diff);
		} else if (Objects.equals(vehicle, "moto")) {
			totalAmount = calculateAmount(totalVehicle, extraCharge, diff).divide(BigDecimal.valueOf(2));
		} else {

		}

		final BigDecimal totalRoundedAmount = calculateRounding(totalAmount, BigDecimal.valueOf(0.5), RoundingMode.UP);

		return totalRoundedAmount;
	}

	/**
	 * Method to calculate the rounding on the totalAmount
	 *
	 * @param totalAmount
	 * @param rounding
	 * @param roundingMode
	 * @return
	 */
	private static BigDecimal calculateRounding(final BigDecimal totalAmount, final BigDecimal rounding,
			final RoundingMode roundingMode) {

		final BigDecimal divided = totalAmount.divide(rounding, 0, roundingMode);
		final BigDecimal result = divided.multiply(rounding);

		return result;
	}

	/**
	 * @param extraCharge
	 * @param diff
	 * @return
	 */
	private static BigDecimal calculateAmount(final int numberOfVehicle, final int extraCharge, final long diff) {
		// TODO HANDLE EXTRACHARGE
		BigDecimal totalAmount = BigDecimal.ZERO;

		if (diff <= 1) {
			totalAmount = BigDecimal.ZERO;
		} else if ((diff > 1) && (diff < 4)) {
			totalAmount = BigDecimal.valueOf((diff - 1) * 2).multiply(BigDecimal.valueOf(numberOfVehicle));
		} else if (diff > 4) {
			totalAmount = BigDecimal.ZERO; // BigDecimal.valueOf(diff-4)
		}
		return totalAmount;
	}

	/**
	 * @param dateList
	 * @return
	 */
	private static long getTimeDifference(final List<Date> dateList) {

		final long diff;
		System.out.println("method : Difference is : -- " + dateList.get(1).getTime() + " fdsfdsogj o : "
				+ dateList.get(0).getTime());
		if (dateList.get(1).getTime() > dateList.get(0).getTime()) {
			diff = ((dateList.get(1).getTime() - dateList.get(0).getTime()) / (60 * 60 * 1000)) % 24;
		} else {
			diff = ((dateList.get(1).getTime() / (60 * 60 * 1000)) % 24) + 24;
		}

		System.out.println("method : Difference is : -- " + diff);
		return diff;

	}

}

// TIME taken
// Edge cases to take into account
// ENtry time - 00
// Exit time - 0
// exit time -24hr toh date calculation
// 18h - 16h baje nikal gayi || TIME if 15h80 ?? ERROR or EXCEPTION ??
// Time calculation 23h to 2 baje
// time if date exceeds more than one day
// Entry date and exit date
// Negative values for time? or time more than 24h
// Incorrect time format
// Exceptions for time hours
// Take date
// end date !< startDate
