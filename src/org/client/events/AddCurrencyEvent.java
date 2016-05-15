package org.client.events;

import org.client.customWidgets.CurrencyTable;
import org.client.services.NotificationService;
import org.shared.Validator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;

public class AddCurrencyEvent extends Composite implements ClickHandler, KeyUpHandler {
	private final CurrencyTable table;
	private final Button addSymbolBtn;
	private final TextBox symbolTxtBox;
	private final NotificationService notificationService;

	public AddCurrencyEvent(CurrencyTable currencyTable, Button addSymblBtn, TextBox symbolTxtBox,
			NotificationService notificationService) {
		this.table = currencyTable;
		this.addSymbolBtn = addSymblBtn;
		this.symbolTxtBox = symbolTxtBox;
		this.notificationService = notificationService;
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
			this.notificationService.clear();
			this.table.addCurrency(symbol);
		} else {
			this.notificationService.notifyInvalidCurrency(Validator.trimInput(symbol));
		}
	}

}
