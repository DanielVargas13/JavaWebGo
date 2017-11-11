package io.github.tpms_coding_buddies.browser;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class Browser {
	private @Getter @Setter String name;
	private @Getter @Setter Properties properties;

	public Browser(String name) {
		this.setName(name);
	}

	public static void main(String[] args) throws URISyntaxException {
		Browser browser = new Browser("Web browser");
		TabbedWindow frame = browser.createWindow("browser:new-tab");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public TabbedWindow createWindow(String uri) throws URISyntaxException {
		return createWindow(new URI(uri));
	}

	public TabbedWindow createWindow(URI uri) {
		return new TabbedWindow(uri);
	}
}