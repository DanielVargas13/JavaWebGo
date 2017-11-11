package io.github.tpms_coding_buddies.browser;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class SpecificURLView extends JComponent {
	public SpecificURLView() {
	}

	public SpecificURLView(URI url) {
		this.setLayout(new BorderLayout());
		this.add(new InvalidProtocolURLView(url), BorderLayout.CENTER);
	}
}
