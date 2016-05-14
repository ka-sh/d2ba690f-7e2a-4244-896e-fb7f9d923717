package org.server.services;

import org.client.services.CurrencyConverterService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * This is to allow different implementations The purpose of this class was to
 * isolate the implementation from the service, and by this I can change to
 * different parsers using the polymorphic behavior.However,If I tried to change
 * the servlet class to ConverterServlet rather than the implementation it will
 * throw exception because it will try to initialize the ConverterService rather than the implementation.
 */
public abstract class ConverterService extends RemoteServiceServlet implements CurrencyConverterService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
