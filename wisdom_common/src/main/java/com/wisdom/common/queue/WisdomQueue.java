package com.wisdom.common.queue;

import org.apache.log4j.Logger;

import java.util.LinkedList;

/**
 * @author  Tony Wang
 * @date 2018/8/8
 */
public class WisdomQueue {
	public static Logger LOG = Logger.getLogger(WisdomQueue.class);

	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Put object into queue and notify all threads
	 */
	public synchronized void put(Object obj) {
		// Add obj to queue if maximum not reached, otherwise DROP!
		int size = elements.size();
		if (size < maxItems) {
//			LOG.debug(size + " items in queue: " + name);
			elements.addLast(obj);
			notifyAll();
		} else {
			LOG.warn("Dropping item from queue: " + name);
		}
	}

	/**
	 * Get object from queue. If none is available wait until notified and then
	 * again check if an object is available.
	 */
	public synchronized Object get() {
		while (true) {
			if (elements.size() > 0) {
				return elements.removeFirst();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					LOG.error("Wait() got interrupted");
				}
			}
		}
	}

	/**
	 * Get object from queue. If none is available, return null.
	 */
	public synchronized Object poll() {
		return elements.poll();
	}

	/**
	 * Flush the queue.
	 */
	public synchronized void flush() {
		elements.clear();
	}

	/**
	 * Returns the max size of the queue.
	 */
	public int getMaxItems() {
		return maxItems;
	}

	/**
	 * Returns the size of the queue.
	 */
	public synchronized int size() {
		return elements.size();
	}

	// Linked list representing queue
	private LinkedList<Object> elements = new LinkedList<Object>();

	// Solely for identifying in trace
	private String name = new String("default");

	// Maximum number allowed in queue, otherwise dropped
	private int maxItems = 200;
}