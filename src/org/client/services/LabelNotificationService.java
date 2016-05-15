package org.client.services;

import java.util.Arrays;

import org.shared.Validator;

import com.google.gwt.user.client.ui.Label;

public class LabelNotificationService implements NotificationService {
	private Label notificationLbl;
	private static String SERVER_ERROR="Error while requesting currency rates from server!";

	public LabelNotificationService(Label notificationLbl) {
		this.notificationLbl = notificationLbl;
	}

	@Override
	public void notify(String msg) {
		notificationLbl.setText(msg);
	}

	@Override
	public void clear() {
		this.notificationLbl.setText("");
	}

	@Override
	public void notifyServerError() {
		this.notificationLbl.setText("Error while requesting currency rates from server!");
	}

	@Override
	public void notifyInvalidCurrency(String symbol) {
		this.notificationLbl.setText("Invalid currency " + symbol + "\n Supported currencies: " + Arrays.toString(Validator.SUPPORTED_CURRENCIES));
	}

	@Override
	public void clearServerErrorNotification() {
		if(this.notificationLbl.getText().equals(SERVER_ERROR)){
			clear();
		}
		
	}

}
