package org.server.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.google.gwt.dev.json.JsonObject;

/**
 * Fixer.io request thread, I need to send two different requests to fixer.io A
 * - Request to fetch the latest prices B - Request to fetch the prices since
 * certain date to calculate the change
 * 
 * @author jblack
 *
 */
public class FixerIORequest extends Thread {
	private final String requestLink;
	private final Map<String, JsonObject> response;
	private final CountDownLatch latch;
	private final String requestType;

	public FixerIORequest(String requestLink, String requestType, Map<String, JsonObject> response,
			CountDownLatch latch) {
		this.requestLink = requestLink;
		this.response = response;
		this.latch = latch;
		this.requestType = requestType;
	}

	@Override
	public void run() {
		try {
			URL url = new URL(this.requestLink);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
			this.response.put(requestType, JsonObject.parse(reader));
			latch.countDown();
		} catch (Exception e) {
			e.printStackTrace();
			this.response.put(requestType,null);
		}
	}
}
