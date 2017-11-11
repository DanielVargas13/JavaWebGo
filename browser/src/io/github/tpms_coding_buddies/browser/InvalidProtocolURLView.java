package io.github.tpms_coding_buddies.browser;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class InvalidProtocolURLView extends SpecificURLView {
	public InvalidProtocolURLView(URI url) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Invalid protocol: " + url.getScheme(), SwingConstants.CENTER), BorderLayout.CENTER);
	}
}

