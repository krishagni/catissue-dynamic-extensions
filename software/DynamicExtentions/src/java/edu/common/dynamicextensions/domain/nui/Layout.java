package edu.common.dynamicextensions.domain.nui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Layout implements Serializable {

	private static final long serialVersionUID = 9161084273291032435L;

	private List<Page> pages;

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Map<String, Object> getProps() {
		List<Map<String, Object>> pageProps = new ArrayList<>();
		if (pages != null) {
			for (Page page : pages) {
				pageProps.add(page.getProps());
			}
		}

		return Collections.singletonMap("pages", pageProps);
	}
}
