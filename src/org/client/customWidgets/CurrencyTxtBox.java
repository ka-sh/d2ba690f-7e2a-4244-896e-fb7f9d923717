package org.client.customWidgets;

import org.client.utilities.Currency;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;

public class CurrencyTxtBox extends TextBox implements KeyUpHandler {
	private final CurrencyTable currencyTable;

	public CurrencyTxtBox(CurrencyTable currencyTable) {
		this.currencyTable = currencyTable;
		addKeyUpHandler(this);
		setFocus(true);
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			String input = this.getText();
			if (!Currency.isValid(input)) {
				Window.alert("Invalid Currency : " + Currency.trimInput(input));
				return;
			}
		
			this.currencyTable.addCurrency(input);
			
		}
	}

}
