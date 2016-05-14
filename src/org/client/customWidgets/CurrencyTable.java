package org.client.customWidgets;

public interface CurrencyTable {

	boolean addCurrency(String currencyCode);

	boolean removeCurrency(String currencyCode);

	/**
	 * Refresh all currencies in the table.
	 */
	void refreshPriceLists();
}
