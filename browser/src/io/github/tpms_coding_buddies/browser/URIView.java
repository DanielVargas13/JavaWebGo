package io.github.tpms_coding_buddies.browser;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Map;

public class URIView extends JComponent {
	private @Getter @Setter URI uri;
	private @Getter URIView uriView;

	public URIView() {}

	public URIView(URI uri) {
		this.setLayout(new BorderLayout());
		this.add(new InvalidProtocolURIView(uri));
	}

	public URIView(URI uri, Map<String, Class<? extends URIView>> uriTypes)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		this.setLayout(new BorderLayout());

		this.setUri(uri);
		this.setUriView(uriTypes.getOrDefault(uri.getScheme(), InvalidProtocolURIView.class)
								.getConstructor(URI.class)
								.newInstance(uri));
	}

	public void setUriView(URIView uriView) {
		this.uriView = uriView;
		this.removeAll();
		this.add(uriView);
	}
}
