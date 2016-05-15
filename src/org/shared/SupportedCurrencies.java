package org.shared;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SupportedCurrencies {
	public final static String[] CURRENCIES = new String[] { "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK",
			"DKK", "GBP", "HRK", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN",
			"RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR" };
	/**
	 * Supported currency for conversion.
	 */
	private final static Set<String> SUPPORTED = new HashSet<>(Arrays.asList(CURRENCIES));

	/**
	 * Symbol is expected to be in upper case and trimmed 
	 * Ex: USD
	 * @param symbol
	 * @return
	 */
	public static boolean isSupported(String symbol) {
		return SUPPORTED.contains(symbol);
	}
}
