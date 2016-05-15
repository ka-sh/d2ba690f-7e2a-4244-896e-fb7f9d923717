package org.server.services;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.shared.Currency;
import org.shared.Validator;

import com.google.gwt.dev.json.JsonObject;
import com.google.gwt.dev.json.JsonValue;

/**
 * Fetching currency data from Fixer.io
 * 
 * @author jblack
 *
 */
public class FixerServiceImpl extends ConverterService {
	private static String HOST = "http://api.fixer.io/";
	private static String LATEST_REQUEST_LINK = "http://api.fixer.io/latest?base=HKD";
	private final static String LATEST_REQ_TYPE = "latest";
	private final static String HISTORICAL_REQ_TYPE = "historical";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Currency[] getCurrencyValue(String[] symbols) {
		try {
			CountDownLatch latch = new CountDownLatch(2);
			Map<String, JsonObject> responses = new ConcurrentHashMap<>(2);
			String symbolParams = getSymbols(symbols);
			new FixerIORequest(getLatestReqLnk(symbolParams), LATEST_REQ_TYPE, responses, latch).start();
			new FixerIORequest(getHistoricalReqLnk(symbolParams), HISTORICAL_REQ_TYPE, responses, latch).start();
			latch.await();
			if (isValidResponse(responses)) {
				Map<String, Double> latestVals = extractResponse(responses.get(LATEST_REQ_TYPE), symbols);
				Map<String, Double> historicalVals = extractResponse(responses.get(HISTORICAL_REQ_TYPE), symbols);
				return getCurrencies(latestVals, historicalVals, symbols);
			}

		} catch (InterruptedException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return getCurrenciesInCaseOfExp(symbols);
	}

	private boolean isValidResponse(Map<String, JsonObject> responses) {
		return !responses.get(HISTORICAL_REQ_TYPE).isEmpty() && !responses.get(LATEST_REQ_TYPE).isEmpty();
	}

	/**
	 * Process latest and previous rates and return array of currencies.
	 * 
	 * @param latest
	 * @param historical
	 * @param symbols
	 * @return
	 */
	private Currency[] getCurrencies(Map<String, Double> latest, Map<String, Double> historical, String[] symbols) {
		ArrayList<Currency> currencies = new ArrayList<>(symbols.length);
		double currVal, prevVal;
		for (String symbol : symbols) {
			if (latest.containsKey(symbol) && historical.containsKey(symbol)) {
				currVal = latest.get(symbol);
				prevVal = historical.get(symbol);
				currencies.add(new Currency(symbol, Double.toString(currVal), calcChange(currVal, prevVal)));
			}
		}
		return currencies.toArray(new Currency[currencies.size()]);
	}

	private String calcChange(double currVal, double prevVal) {
		double diff = currVal - prevVal;
		double prcnt = 100.0 * diff / currVal;
		DecimalFormat numberFormatter = new DecimalFormat("#.00000");
		return numberFormatter.format(diff) + "(" + numberFormatter.format(prcnt) + "%)";
	}

	/*
	 * Ex:http://api.fixer.io/latest?base=HKD&symbols=CZK
	 */
	private String getLatestReqLnk(String symbols) {
		return LATEST_REQUEST_LINK + "&" + symbols;
	}

	/*
	 * Return request used to fetch historical data. Ex:
	 * http://api.fixer.io/2016-05-12?base=HKD&symbols=CZK
	 */
	private String getHistoricalReqLnk(String symbols) {
		String link = HOST + getPrevDate() + "?base=HKD&" + symbols;
		return link;
	}

	/**
	 * Return previous date in format:yyyy-MM-dd Used to fetch the historical
	 * data for currencies.
	 * 
	 * @return
	 */
	private String getPrevDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -7);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(c.getTime());
	}

	private Currency[] getCurrenciesInCaseOfExp(String[] symbols) {
		Currency[] currencies = new Currency[symbols.length];
		for (int i = 0; i < symbols.length; i++) {
			currencies[i] = new Currency(symbols[i], "-1", "-1");
		}
		return currencies;
	}

	private Map<String, Double> extractResponse(JsonObject response, String[] symbols) {
		Map<String, Double> values = new HashMap<>();
		JsonObject rates = response.get("rates").asObject();
		JsonValue val;
		for (String symbol : symbols) {
			val = rates.get(symbol);
			if (val != null) {
				values.put(symbol, val.asNumber().getDecimal());
			}
		}
		return values;
	}

	private String getSymbols(String[] symbols) {
		String concatSymbols = "symbols=";
		for (String symbol : symbols) {
			if (Validator.isValidSymbol(symbol)) {
				concatSymbols += symbol + ",";
			}

		}
		return concatSymbols;
	}

}
