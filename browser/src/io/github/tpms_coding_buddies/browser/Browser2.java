package io.github.tpms_coding_buddies.browser;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class Browser2 {
	private @Getter @Setter String name;
	private @Getter @Setter Properties properties;

	public Browser2(String name) {
		this.setName(name);
	}

	public static void main(String[] args) throws URISyntaxException {
		Browser2 browser = new Browser2("Web browser");
		TabbedWindow frame = browser.createWindow("browser:new-tab");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public TabbedWindow createWindow(String url) throws URISyntaxException {
		return createWindow(new URI(url));
	}

	public TabbedWindow createWindow(URI url) {
		return new TabbedWindow(url);
	}
}
