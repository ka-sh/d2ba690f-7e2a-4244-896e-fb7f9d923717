package org.client.services;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class StandardStyler implements StylerService {

	@Override
	public void styleCurrencyTabel(FlexTable currencyTable) {
		currencyTable.getRowFormatter().addStyleName(0, "watchListHeader");
		currencyTable.addStyleName("watchList");
		currencyTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
		currencyTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
		currencyTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
	}

	public void styleCurrencyTableRow(FlexTable currencyTable) {
		int row = currencyTable.getRowCount();
		currencyTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
		currencyTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
		currencyTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
	}

	@Override
	public void styleAddPanel(HorizontalPanel addPanel) {
		addPanel.addStyleName("addPanel");
	}

	@Override
	public void styleChangeWidget(Label change) {
		if (change.getText().contains("-")) {
			change.setStyleName("negativeChange");
		} else {
			change.setStyleName("positiveChange");
		}

	}

}
