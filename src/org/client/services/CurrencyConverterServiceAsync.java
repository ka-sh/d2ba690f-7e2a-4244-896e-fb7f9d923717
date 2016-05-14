package org.client.services;

import org.shared.Currency;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CurrencyConverterServiceAsync {
	void getCurrencyValue(String[] currencies, AsyncCallback<Currency[]> callback);
}
