package org.shared;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {
	public final static String[] SUPPORTED_CURRENCIES = new String[] {"AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK",
			"DKK", "GBP", "HRK", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN",
			"RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR" };
	/**
	 * Supported currency for conversion.
	 */
	private final static Set<String> SUPPORTED = new HashSet<>(Arrays.asList(SUPPORTED_CURRENCIES));

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

		return SUPPORTED.contains(symbol);
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
