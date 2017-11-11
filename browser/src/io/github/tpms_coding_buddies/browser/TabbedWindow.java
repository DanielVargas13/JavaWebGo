package io.github.tpms_coding_buddies.browser;

import lombok.Getter;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TabbedWindow extends JFrame {
	private @Getter final List<URIView> tabs;
	private @Getter final JTabbedPane tabbedPane;

	public TabbedWindow(String uri) throws URISyntaxException {
		this(new URI(uri));
	}

	public TabbedWindow(URI uri) {
		this.tabs = new ArrayList<>();
		this.tabbedPane = new JTabbedPane();
		this.createTab(uri);
		this.add(this.getTabbedPane());
		this.pack();
	}

	public URIView createTab(URI uri) {
		URIView tab = new URIView(uri);
		this.tabs.add(tab);
		this.getTabbedPane().addTab(uri.toString(), tab);
		tab.addPropertyChangeListener("title",
									  e -> this.getTabbedPane()
											   .setTitleAt(this.getTabbedPane().indexOfComponent(tab),
														   (String) e.getNewValue()));
		return tab;
	}
}
