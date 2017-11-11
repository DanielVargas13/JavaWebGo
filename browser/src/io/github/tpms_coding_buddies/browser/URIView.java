package io.github.tpms_coding_buddies.browser;

import io.github.tpms_coding_buddies.browser.protocols.InvalidProtocolURIView;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class URIView extends JComponent {
	private @Getter @Setter URI uri;
	private @Getter SpecificURIView uriView;

	private URIView() {
		this.setLayout(new BorderLayout());
	}

	public URIView(String uri) throws URISyntaxException {
		this(new URI(uri));
	}

	public URIView(URI uri) {
		this();
		this.add(new InvalidProtocolURIView(uri));
	}

	public URIView(String uri, Map<String, Class<? extends SpecificURIView>> uriTypes)
			throws URISyntaxException, NoSuchMethodException, IllegalAccessException, InstantiationException,
				   InvocationTargetException {
		this(new URI(uri), uriTypes);
	}

	public URIView(URI uri, Map<String, Class<? extends SpecificURIView>> uriTypes)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		this();

		this.setUri(uri);
		this.setUriView(uriTypes.getOrDefault(uri.getScheme(), InvalidProtocolURIView.class)
								.getConstructor(URI.class)
								.newInstance(uri));
	}

	@Tolerate
	public void setUri(String uri) throws URISyntaxException {
		this.setUri(new URI(uri));
	}

	public void setUriView(SpecificURIView uriView) {
		this.uriView = uriView;
		this.removeAll();
		this.add(uriView);
	}
}
