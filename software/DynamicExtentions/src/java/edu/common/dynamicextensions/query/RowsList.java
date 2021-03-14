package edu.common.dynamicextensions.query;

import java.util.Iterator;

import edu.common.dynamicextensions.query.cachestore.LinkedEhCacheMap;

public class RowsList {
	private LinkedEhCacheMap<Integer, Object[]> rows = new LinkedEhCacheMap<>();

	public void add(Object[] row) {
		rows.put(rows.size(), row);
	}

	public int size() {
		return rows.size();
	}

	public Iterator<Object[]> iterator() {
		return rows.iterator();
	}

	public void cleanup() {
		rows.destroy();
		rows = null;
	}
}
