package org.client.customWidgets;

public interface CurrencyTable {
	boolean addCurrency(String currencyCode);

	boolean removeCurrency(String currencyCode);

	void refreshPriceLists();
}
