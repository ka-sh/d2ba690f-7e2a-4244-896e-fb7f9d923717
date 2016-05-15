package org.client.services;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/*
 * add CSS style to widgets.
 */
public interface StylerService {
	/**
	 * Style currency table.
	 * 
	 * @param currencyTable
	 */
	void styleCurrencyTabel(FlexTable currencyTable);

	/**
	 * Style newly added rows
	 * 
	 * @param currencyTable
	 */
	void styleCurrencyTableRow(FlexTable currencyTable);
	/**
	 * Style add currency panel
	 */
	void styleAddPanel(HorizontalPanel addPanel);
	
	/**
	 * Change widget based on the value
	 */
	void styleChangeWidget(Label change);
}
