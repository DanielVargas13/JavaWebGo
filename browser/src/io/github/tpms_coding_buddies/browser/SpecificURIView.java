package io.github.tpms_coding_buddies.browser;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class SpecificURIView extends JComponent {
	public SpecificURIView() {
	}

	public SpecificURIView(URI uri) {
		this.setLayout(new BorderLayout());
		this.add(new InvalidProtocolURIView(uri), BorderLayout.CENTER);
	}
}
