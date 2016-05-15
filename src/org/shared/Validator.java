package org.shared;

public class Validator {

	/**
	 * Currency is only valid if it is only characters, and length is no long
	 * than 3
	 * 
	 * @param symbol
	 * @return
	 */
	public static boolean isValidSymbol(String symbol) {

		if (symbol == null || symbol.isEmpty() || !IsValidFormat(symbol)) {
			return false;
		}

		return SupportedCurrencies.isSupported(symbol);
	}

	private static boolean IsValidFormat(String symbol) {
		return symbol.trim().matches("^[A-Z\\.]{1,3}$");
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
