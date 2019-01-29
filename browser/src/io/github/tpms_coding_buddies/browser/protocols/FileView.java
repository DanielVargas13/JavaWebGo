package io.github.tpms_coding_buddies.browser.protocols;

import io.github.tpms_coding_buddies.browser.SpecificURIView;
import io.github.tpms_coding_buddies.browser.URIView;
import io.github.tpms_coding_buddies.browser.formats.TextView;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Map;

public class FileView extends URIView {
	FileView(URI uri, Map<String, Class<FormatView>> formatMap)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		String tmp = uri.getPath();
		int tmp2 = tmp.lastIndexOf(File.separatorChar);
		tmp = (tmp2 > 0) ? tmp.substring(tmp2 + 1) : "";
		tmp2 = tmp.lastIndexOf(File.separatorChar);
		tmp = (tmp2 > 0) ? tmp.substring(tmp2 + 1) : "";

		this.add(formatMap.getOrDefault(tmp, TextView.class).getConstructor(URI.class).newInstance(uri));
	}
}
