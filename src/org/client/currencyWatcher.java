package org.client;

import java.util.Date;

import org.client.customWidgets.AddCurrencyBtn;
import org.client.customWidgets.CurrencyTable;
import org.client.customWidgets.CurrencyTxtBox;
import org.client.customWidgets.CustomCurrencyTable;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class currencyWatcher implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();
	private CurrencyTable currencyTable = new CustomCurrencyTable();
	private HorizontalPanel addCurrencyPanel = new HorizontalPanel();
	private Label lastUpdateLbl = new Label();
	private CurrencyTxtBox currencyTxtBx = new CurrencyTxtBox(currencyTable);
	private AddCurrencyBtn addCurrBtn = new AddCurrencyBtn("Add", currencyTable, currencyTxtBx);

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		initAddCurrPanel();
		initMainPanel();
		initRootPanel();
		currencyTxtBx.setFocus(true);
		Timer t = new Timer() {

			@Override
			public void run() {
				currencyTable.refreshPriceLists();
				refreshLastUpdateLbl();
			}
		};
		t.scheduleRepeating(700);
	}

	private void initAddCurrPanel() {
		this.addCurrencyPanel.add(addCurrBtn);
		this.addCurrencyPanel.add(this.currencyTxtBx);
	}

	private void initMainPanel() {
		this.mainPanel.add((Widget) this.currencyTable);
		this.mainPanel.add(this.addCurrencyPanel);
		this.mainPanel.add(this.lastUpdateLbl);
	}

	private void initRootPanel() {
		RootPanel.get("currencyList").add(this.mainPanel);
	}
	private void refreshLastUpdateLbl(){
		// Display timestamp showing last refresh.
	      DateTimeFormat dateFormat = DateTimeFormat.getFormat(
	        DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM);
	      lastUpdateLbl.setText("Last update : " 
	        + dateFormat.format(new Date()));
	}

}
