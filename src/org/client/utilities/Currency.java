package org.client.utilities;

public class Currency {
	/**
	 * Currency is only valid if it is only charachters, and length is no long
	 * than 3
	 * 
	 * @param currency
	 * @return
	 */
	public static boolean isValid(String currency) {

		if (currency == null || currency.isEmpty()) {
			return false;
		}

		return currency.trim().matches("^[A-Z\\.]{1,3}$");
	}

	/**
	 * Incase of the user inserted more than 4 characters
	 * 
	 * @param input
	 * @return Trimmed input to only 4 characters long.
	 */
	public static String trimInput(String input) {
		if (input.length() > 4) {
			return input.substring(0, 4) + "...";
		}
		return input;
	}
}
