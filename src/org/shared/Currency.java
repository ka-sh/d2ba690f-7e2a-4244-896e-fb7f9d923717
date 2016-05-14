package org.shared;

import java.io.Serializable;

/**
 * Contain Currency information, and currency related logic.
 * 
 * @author jblack
 *
 */
public class Currency implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String currentVal;
	private String change;
	private String symbol;

	public Currency() {
	}

	public Currency(String symbol, String val, String change) {
		this.currentVal = val;
		this.symbol = symbol;
		this.change = change;
	}

	

	public String getCurrentVal() {
		return currentVal;
	}

	public void setCurrentVal(String currentVal) {
		this.currentVal = currentVal;
	}

	public String getChange() {
		return change;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
