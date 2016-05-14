package org.client.events;

import org.client.customWidgets.CurrencyTable;
import org.shared.Validator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class AddCurrencyEvent extends Composite implements ClickHandler, KeyUpHandler {
	private final CurrencyTable table;
	private final Button addSymbolBtn;
	private final TextBox symbolTxtBox;
	private final Label notificationLbl;

	public AddCurrencyEvent(CurrencyTable currencyTable, Button addSymblBtn, TextBox symbolTxtBox,
			Label notificationLbl) {
		this.table = currencyTable;
		this.addSymbolBtn = addSymblBtn;
		this.symbolTxtBox = symbolTxtBox;
		this.notificationLbl = notificationLbl;
		this.addSymbolBtn.addClickHandler(this);
		this.symbolTxtBox.addKeyUpHandler(this);

	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			addCurrencyToTable();
		}

	}

	@Override
	public void onClick(ClickEvent event) {
		addCurrencyToTable();

	}

	private void addCurrencyToTable() {
		String input = this.symbolTxtBox.getText();
		if (input == null) {
			return;
		}
		String symbol = input.toUpperCase();
		if (Validator.isValidSymbol(symbol)) {
			this.notificationLbl.setText("");
			this.table.addCurrency(symbol);
		} else {
			this.notificationLbl.setText(("Invalid Currency : " + Validator.trimInput(symbol)));
		}
	}

}
