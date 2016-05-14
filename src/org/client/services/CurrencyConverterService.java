package org.client.services;

import org.shared.Currency;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("currency")
public interface CurrencyConverterService extends RemoteService {
	Currency[] getCurrencyValue(String[] currencies);
}
