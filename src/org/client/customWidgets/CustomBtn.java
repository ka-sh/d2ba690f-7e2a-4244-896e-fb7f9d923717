package org.client.customWidgets;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

/**
 * General purpose button
 * @author jblack
 *
 */
public abstract class CustomBtn extends Button implements ClickHandler {
	public CustomBtn(final String caption) {
		super(caption);
		addClickHandler(this);
	}
}
