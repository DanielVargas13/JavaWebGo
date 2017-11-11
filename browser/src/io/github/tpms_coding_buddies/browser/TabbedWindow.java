package io.github.tpms_coding_buddies.browser;

import lombok.Getter;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TabbedWindow extends JFrame {
	private @Getter final List<URLView> tabs;
	private @Getter final JTabbedPane tabbedPane;

	public TabbedWindow(String url) throws URISyntaxException {
		this(new URI(url));
	}

	public TabbedWindow(URI url) {
		this.tabs = new ArrayList<>();
		this.tabbedPane = new JTabbedPane();
		this.createTab(url);
		this.add(this.getTabbedPane());
		this.pack();
	}

	public URLView createTab(URI url) {
		URLView tab = new URLView(url);
		this.tabs.add(tab);
		this.getTabbedPane().addTab(url.toString(), tab);
		tab.addPropertyChangeListener("title",
									  e -> this.getTabbedPane()
											   .setTitleAt(this.getTabbedPane().indexOfComponent(tab),
														   (String) e.getNewValue()));
		return tab;
	}
}
