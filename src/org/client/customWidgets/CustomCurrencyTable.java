package org.client.customWidgets;

import java.util.ArrayList;

import org.client.services.CurrencyConverterServiceAsync;
import org.shared.Currency;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

public class CustomCurrencyTable extends FlexTable implements CurrencyTable {
	private ArrayList<String> symbols;
	private final static int Code_Column = 0;
	private final static int VAL_COLUMN = 1;
	private final static int CHANGE_COL = 2;
	private final static int Remove_Column = 3;
	private final CurrencyConverterServiceAsync converterSrvs;

	public CustomCurrencyTable(CurrencyConverterServiceAsync converterSrvs) {
		this.symbols = new ArrayList<>();
		this.setText(0, 0, "Currency");
		this.setText(0, 1, "Price");
		this.setText(0, 2, "Change");
		this.setText(0, 3, "Remove");
		this.converterSrvs = converterSrvs;
	}

	@Override
	public boolean addCurrency(String currencyCode) {
		if (symbols.contains(currencyCode)) {
			return false;
		}
		this.symbols.add(currencyCode);
		int row = getRowCount();
		this.setText(row, Code_Column, currencyCode);
		this.setWidget(row, Remove_Column, new RemoveButton(currencyCode));
		refreshPriceLists();
		return true;
	}

	@Override
	public boolean removeCurrency(String currencyCode) {
		int removeIndex = this.symbols.indexOf(currencyCode);
		this.symbols.remove(removeIndex);
		removeRow(removeIndex + 1);
		return true;
	}

	public void refreshPriceLists() {
		if (!this.symbols.isEmpty()) {
			this.converterSrvs.getCurrencyValue(this.symbols.toArray(new String[symbols.size()]),
					new AsyncCallback<Currency[]>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onSuccess(Currency[] result) {
							updateTable(result);
						}
					});
		}

	}

	/**
	 * Update all table currencies
	 * 
	 * @param currencies
	 */
	public void updateTable(Currency[] currencies) {
		for (Currency currency : currencies) {
			refreshRow(currency);
		}
	}

	/**
	 * Update single row
	 * 
	 * @param currency
	 */
	public void refreshRow(Currency currency) {
		int index = symbols.indexOf(currency.getSymbol());
		if (index > -1) {
			this.setText(index + 1, VAL_COLUMN, currency.getCurrentVal());
			this.setText(index + 1, CHANGE_COL, currency.getChange());
		}
	}

	/**
	 * Remove button for removing currencies from table.
	 * 
	 * @author jblack
	 *
	 */
	private class RemoveButton extends Button implements ClickHandler {
		private String symbol;

		public RemoveButton(String code) {
			super("x");
			addClickHandler(this);
			this.symbol = code;
		}

		@Override
		public void onClick(ClickEvent event) {
			removeCurrency(symbol);
		}

	}

}
