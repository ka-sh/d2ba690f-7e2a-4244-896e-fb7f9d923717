package org.client.customWidgets;

import java.util.ArrayList;

import org.client.services.CurrencyConverterServiceAsync;
import org.client.services.NotificationService;
import org.client.services.StylerService;
import org.shared.Currency;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class CustomCurrencyTable extends FlexTable implements CurrencyTable {
	private ArrayList<String> symbols;
	private final static int Code_Column = 0;
	private final static int VAL_COLUMN = 1;
	private final static int CHANGE_COL = 2;
	private final static int Remove_Column = 3;
	private final CurrencyConverterServiceAsync converterService;
	private final NotificationService notificationService;
	private final StylerService styler;

	public CustomCurrencyTable(CurrencyConverterServiceAsync converterSrvs, NotificationService notificationService,
			StylerService styler) {
		this.symbols = new ArrayList<>();
		setHeader();
		this.converterService = converterSrvs;
		this.notificationService = notificationService;
		/**
		 * Add CSS styles
		 */
		this.styler = styler;
		this.styler.styleCurrencyTabel(this);
	}

	private void setHeader() {
		this.setText(0, 0, "Currency");
		this.setText(0, 1, "Price");
		this.setText(0, 2, "Change");
		this.setText(0, 3, "Remove");
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

	@Override
	public void refreshPriceLists() {
		if (!this.symbols.isEmpty()) {
			try {
				this.converterService.getCurrencyValue(this.symbols.toArray(new String[symbols.size()]),
						new UpdateTableCallBack());
			} catch (Throwable e) {
				notificationService.notifyServerError();
				e.printStackTrace();
			}

		}

	}

	private void updateTable(Currency[] currencies) {
		for (Currency currency : currencies) {
			refreshRow(currency);
		}
	}

	private void refreshRow(Currency currency) {
		int index = symbols.indexOf(currency.getSymbol());
		if (index > -1) {
			if (currency.getChange().equals("-1") || currency.getCurrentVal().equals("-1")) {
				notificationService.notifyServerError();
			} else {
				notificationService.clearServerErrorNotification();
				this.setText(index + 1, VAL_COLUMN, currency.getCurrentVal());
				Label changeLabl = new Label(currency.getChange());
				styler.styleChangeWidget(changeLabl);
				this.setWidget(index + 1, CHANGE_COL, changeLabl);
			}

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

	private class UpdateTableCallBack implements AsyncCallback<Currency[]> {
		@Override
		public void onFailure(Throwable caught) {
			notificationService.notifyServerError();
		}

		@Override
		public void onSuccess(Currency[] result) {
			updateTable(result);
		}
	}

}
