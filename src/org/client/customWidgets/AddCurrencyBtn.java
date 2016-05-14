package org.client.customWidgets;

import org.client.utilities.Currency;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

public class AddCurrencyBtn extends Button implements ClickHandler {
	private final CurrencyTable currencyTable;
	private final TextBox currencyTxtBox;

	public AddCurrencyBtn(final String caption, CurrencyTable currencyTable, TextBox currencyTxtBox) {
		super(caption);
		this.currencyTable = currencyTable;
		this.currencyTxtBox = currencyTxtBox;
		this.addClickHandler(this);

	}

	@Override
	public void onClick(ClickEvent event) {
		String input = this.currencyTxtBox.getText();
		if (!Currency.isValid(input)) {
			Window.alert("Invalid Currency : " + Currency.trimInput(input));
			return;
		}
		this.currencyTable.addCurrency(input);
	
	}

}
