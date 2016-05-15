package org.client.services;

public interface NotificationService {
	/**
	 * Display notification message to the user.
	 * 
	 * @param msg
	 */
	void notify(final String msg);
	
	/**
	 * Clear current messages.
	 */
	void clear();
	/**
	 * Notify server error
	 */
	void notifyServerError();
	/**
	 * Notify Invalid currency
	 */
	void notifyInvalidCurrency(String symbol);
	/**
	 * Clear server error messages only, while keeping invalid currency
	 */
	void clearServerErrorNotification();
}
