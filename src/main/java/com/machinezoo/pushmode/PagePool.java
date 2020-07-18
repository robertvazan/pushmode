// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode;

import java.time.*;
import java.util.*;
import org.slf4j.*;
import com.machinezoo.noexception.*;
import com.machinezoo.stagean.*;
import io.micrometer.core.instrument.*;

/**
 * A set of page IDs and associated page instances the server knows about.
 */
@StubDocs
@DraftApi("should be pluggable and configurable, allow off-memory storage")
@StubCode("use existing cache implementations")
public class PagePool {
	private static final PagePool instance = new PagePool();
	public static PagePool instance() {
		return instance;
	}
	private static final Logger logger = LoggerFactory.getLogger(PagePool.class);
	private Map<String, PoolEntry> pool = Metrics.gauge("pushmode.pool.size", new HashMap<>(), m -> instance.size());
	private Duration neverExpireBefore = Duration.ofSeconds(70);
	private Duration alwaysExpireAfter = Duration.ofMinutes(10);
	private int capacity = 2000;
	private double purgeRatio = 0.8;
	private int purgeThreshold = capacity;
	private PagePool() {
	}
	public synchronized void add(PushPage page) {
		PoolEntry entry = new PoolEntry(page);
		pool.put(page.pageId(), entry);
		Exceptions.log(logger).run(this::purge);
	}
	public synchronized PushPage lookup(String id) {
		PoolEntry entry = pool.get(id);
		if (entry != null) {
			entry.access();
			return entry.page;
		}
		return null;
	}
	private void purge() {
		if (pool.size() >= purgeThreshold) {
			List<PoolEntry> sorted = new ArrayList<>(pool.values());
			sorted.sort(Comparator.comparing(e -> e.lastAccessTime));
			Instant now = Instant.now();
			Instant hardLimit = now.minus(alwaysExpireAfter);
			Instant softLimit = now.minus(neverExpireBefore);
			int targetSize = (int)(purgeRatio * capacity);
			for (PoolEntry entry : sorted) {
				if (entry.lastAccessTime.isBefore(hardLimit) || pool.size() > targetSize && entry.lastAccessTime.isBefore(softLimit))
					pool.remove(entry.page.pageId());
				else
					break;
			}
			purgeThreshold = Math.max((int)(pool.size() / purgeRatio), capacity);
		}
	}
	private synchronized int size() {
		return pool.size();
	}
	private class PoolEntry {
		final PushPage page;
		PoolEntry(PushPage page) {
			this.page = page;
		}
		Instant lastAccessTime = Instant.now();
		void access() {
			lastAccessTime = Instant.now();
		}
	}
}
