package org.server.serviceTest;

import org.junit.Test;
import org.server.services.FixerServiceImpl;

/**
 * fixerService Integration test.
 */

public class TestConverterService {

	@Test
	public void testGetValidCurrency() {
		new FixerServiceImpl().getCurrencyValue(new String[] { "USD","CZK"});
	}
	//3.0672
	
}
