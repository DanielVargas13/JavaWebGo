package io.github.tpms_coding_buddies.browser;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class InvalidProtocolURIView extends URIView {
	public InvalidProtocolURIView(URI uri) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Invalid protocol: " + uri.getScheme(), SwingConstants.CENTER), BorderLayout.CENTER);
	}
}

