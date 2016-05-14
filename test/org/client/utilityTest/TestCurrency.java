package org.client.utilityTest;

import org.client.utilities.Currency;
import org.junit.Test;

public class TestCurrency {

	@Test
	public void testNullEmptyCurrency() {
		org.junit.Assert.assertFalse(Currency.isValid(null));
		org.junit.Assert.assertFalse(Currency.isValid(""));
	}

	@Test
	public void testInvalidFormat() {
		org.junit.Assert.assertFalse(Currency.isValid("123"));
		org.junit.Assert.assertFalse(Currency.isValid("_w23"));
		org.junit.Assert.assertFalse(Currency.isValid("_$@##@$"));
	}

	@Test
	public void testinvalidLength() {
		org.junit.Assert.assertFalse(Currency.isValid("_w23asdfsadfadsf"));
	}

	@Test
	public void testValidCurrency() {
		org.junit.Assert.assertTrue(Currency.isValid("HKD"));
	}

}
