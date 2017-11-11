package io.github.tpms_coding_buddies.browser.protocols;

import io.github.tpms_coding_buddies.browser.SpecificURIView;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class InvalidProtocolURIView extends SpecificURIView {
	public InvalidProtocolURIView(URI uri) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Invalid protocol: " + uri.getScheme(), SwingConstants.CENTER), BorderLayout.CENTER);
	}
}

