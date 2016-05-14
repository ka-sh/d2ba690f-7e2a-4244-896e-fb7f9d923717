package org.client.utilities;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * Responsible of fetching the currency prices
 * @author jblack
 *
 */
import com.sun.javafx.collections.MappingChange.Map;

public abstract class CurrencyWatcher {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Return the currency currency value for specific currency.
	 * @param currencyCode
	 * @return
	 */
	public abstract String getValue(String currencyCode);


}
