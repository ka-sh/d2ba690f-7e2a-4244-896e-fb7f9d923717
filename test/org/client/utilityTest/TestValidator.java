package org.client.utilityTest;

import org.junit.Test;
import org.shared.Validator;

public class TestValidator {

	@Test
	public void testNullEmptyCurrency() {
		org.junit.Assert.assertFalse(Validator.isValidSymbol(null));
		org.junit.Assert.assertFalse(Validator.isValidSymbol(""));
	}

	@Test
	public void testInvalidFormat() {
		org.junit.Assert.assertFalse(Validator.isValidSymbol("123"));
		org.junit.Assert.assertFalse(Validator.isValidSymbol("_w23"));
		org.junit.Assert.assertFalse(Validator.isValidSymbol("_$@##@$"));
	}

	@Test
	public void testinvalidLength() {
		org.junit.Assert.assertFalse(Validator.isValidSymbol("_w23asdfsadfadsf"));
	}

	@Test
	public void testValidCurrency() {
		org.junit.Assert.assertTrue(Validator.isValidSymbol("HKD"));
	}

}
