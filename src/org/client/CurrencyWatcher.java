package org.client;

import java.util.Date;

import org.client.customWidgets.CurrencyTable;
import org.client.customWidgets.CustomCurrencyTable;
import org.client.customWidgets.SymbolsSuggestBox;
import org.client.events.AddCurrencyEvent;
import org.client.services.CurrencyConverterService;
import org.client.services.CurrencyConverterServiceAsync;
import org.client.services.LabelNotificationService;
import org.client.services.NotificationService;
import org.client.services.StandardStyler;
import org.client.services.StylerService;
import org.shared.SupportedCurrencies;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CurrencyWatcher implements EntryPoint {
	private final CurrencyConverterServiceAsync currencyService = GWT.create(CurrencyConverterService.class);
	private VerticalPanel mainPanel = new VerticalPanel();
	private StylerService styler = new StandardStyler();
	private HorizontalPanel addCurrencyPanel = new HorizontalPanel();
	private Label lastUpdateLbl = new Label();
	private Label notificationLbl = new Label();
	private static int REFRESH_INTERVALS = 15000;
	private Button addSymbBtn = new Button("Add");
	private SymbolsSuggestBox symbTxtBox = new SymbolsSuggestBox(SupportedCurrencies.CURRENCIES);
	private NotificationService notificationService = new LabelNotificationService(notificationLbl);
	private CurrencyTable currencyTable = new CustomCurrencyTable(currencyService, notificationService, styler);
	private AddCurrencyEvent event = new AddCurrencyEvent(currencyTable, addSymbBtn, symbTxtBox, notificationService);
	private Label historyNotification = new Label("All changes are calculated based on 1 month old historical data.");

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		initAddSymbolPanel();
		initMainPanel();
		initRootPanel();
		symbTxtBox.setFocus(true);
		Timer t = new Timer() {
			@Override
			public void run() {
				currencyTable.refreshPriceLists();
				refreshLastUpdateLbl();
			}
		};
		t.scheduleRepeating(REFRESH_INTERVALS);
	}

	private void initAddSymbolPanel() {
		this.addCurrencyPanel.add(addSymbBtn);
		this.addCurrencyPanel.add(symbTxtBox);
		styler.styleAddPanel(addCurrencyPanel);
	}

	private void initMainPanel() {
		this.mainPanel.add(historyNotification);
		this.mainPanel.add((Widget) this.currencyTable);
		this.mainPanel.add(this.addCurrencyPanel);
		this.mainPanel.add(this.lastUpdateLbl);
		this.mainPanel.add(this.notificationLbl);

	}

	private void initRootPanel() {
		RootPanel.get("currencyList").add(this.mainPanel);
	}

	private void refreshLastUpdateLbl() {
		// Display timestamp showing last refresh.
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM);
		lastUpdateLbl.setText("Last update : " + dateFormat.format(new Date()));
	}

}
