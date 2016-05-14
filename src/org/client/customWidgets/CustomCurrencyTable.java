package org.client.customWidgets;

import java.util.ArrayList;

import org.client.utilities.CurrencyWatcher;
import org.client.utilities.FixerCurrencyWatcher;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

public class CustomCurrencyTable extends FlexTable implements CurrencyTable {
	private ArrayList<String> currencies;
	private final static int Code_Column = 0;
	private final static int VAL_COLUMN = 1;
	private final static int Remove_Column = 3;
	private CurrencyWatcher watcher = new FixerCurrencyWatcher();

	public CustomCurrencyTable() {
		this.currencies = new ArrayList<>();
		this.setText(0, 0, "Currency");
		this.setText(0, 1, "Price");
		this.setText(0, 2, "Change");
		this.setText(0, 3, "Remove");
	}

	@Override
	public boolean addCurrency(String currencyCode) {
		if (currencies.contains(currencyCode)) {
			return false;
		}
		this.currencies.add(currencyCode);
		int row = getRowCount();
		this.setText(row, Code_Column, currencyCode);
		this.setWidget(row, Remove_Column, new RemoveButton(currencyCode));
		return true;
	}

	@Override
	public boolean removeCurrency(String currencyCode) {
		int removeIndex = this.currencies.indexOf(currencyCode);
		this.currencies.remove(removeIndex);
		removeRow(removeIndex + 1);
		return true;
	}

	public void refreshPriceLists() {
		for (String code : currencies) {
			refreshRow(code);
		}
	}

	public void refreshRow(String code) {
		String val = watcher.getValue(code);
		int index = currencies.indexOf(code);
		if(index>-1){
			this.setText(index + 1, VAL_COLUMN, val);	
		}
		
	}

	private class RemoveButton extends Button implements ClickHandler {
		private String code;

		public RemoveButton(String code) {
			super("x");
			addClickHandler(this);
			this.code = code;
		}

		@Override
		public void onClick(ClickEvent event) {
			removeCurrency(code);
		}

	}

}
