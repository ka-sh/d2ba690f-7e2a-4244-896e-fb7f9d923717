package org.client.customWidgets;

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;

public class SymbolsSuggestBox extends SuggestBox {
	/**
	 * Array of supported currencies
	 * @param supportedCurrencies
	 */
	public SymbolsSuggestBox(String[] supportedCurrencies) {
		super(getSuggestData(supportedCurrencies));
		
	}

	private static MultiWordSuggestOracle getSuggestData(String[] supportedCurrencies) {
		MultiWordSuggestOracle choices = new MultiWordSuggestOracle();
		for (String symbol : supportedCurrencies) {
			choices.add(symbol);
		}
		return choices;
	}
}
