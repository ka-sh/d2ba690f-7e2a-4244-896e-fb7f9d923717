package org.client.utilities;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Random;

/**
 * Fetch values from xe site
 * 
 * @author jblack
 *
 */
public class FixerCurrencyWatcher extends CurrencyWatcher {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private static String CONVERT_TO = "http://api.fixer.io/latest?base=HKD&symbols=";
	

	@Override
	public String getValue(String currencyCode) {
		return NumberFormat.getFormat("#,##0.00").format(
				Random.nextDouble()*100);
		
	}


}
