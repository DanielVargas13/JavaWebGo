package io.github.tpms_coding_buddies.browser;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class URLView extends JComponent {
	private @Getter @Setter URI url;
	private @Getter SpecificURLView urlView;

	private URLView() {
		this.setLayout(new BorderLayout());
	}

	public URLView(String url) throws URISyntaxException {
		this(new URI(url));
	}

	public URLView(URI url) {
		this();
		this.add(new InvalidProtocolURLView(url));
	}

	public URLView(String url, Map<String, Class<? extends SpecificURLView>> urlTypes)
			throws URISyntaxException, NoSuchMethodException, IllegalAccessException, InstantiationException,
				   InvocationTargetException {
		this(new URI(url), urlTypes);
	}

	public URLView(URI url, Map<String, Class<? extends SpecificURLView>> urlTypes)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		this();

		this.setUrl(url);
		this.setUrlView(urlTypes.getOrDefault(url.getScheme(), InvalidProtocolURLView.class)
								.getConstructor(URI.class)
								.newInstance(url));
	}

	@Tolerate
	public void setUrl(String url) throws URISyntaxException {
		this.setUrl(new URI(url));
	}

	public void setUrlView(SpecificURLView urlView) {
		this.urlView = urlView;
		this.removeAll();
		this.add(urlView);
	}
}
