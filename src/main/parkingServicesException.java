package main;

public class parkingServicesException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 *
	 * @param message
	 *            the error message
	 */
	public parkingServicesException(final String message) {
		super(message);
	}

	/**
	 * Default constructor with the cause
	 *
	 * @param message
	 *            the error message
	 * @param cause
	 *            the error cause
	 */
	public parkingServicesException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
