package com.wisdom.iwcs.service.task.subtask.intf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Used for event observer from rabbitMQ, this class will receiver events from rabbitMQ, and
 * push to proper listeners.
 * Can extends or include this class to implement the message receive works from message queue.
 *
 * @author : Tony Wang
 * @date  2019-6-27
 */
public class WcsObservable{
    private static Logger logger = LoggerFactory.getLogger(WcsObservable.class);
    private Map<String, Set<IWcsObserver>> listeners = new ConcurrentHashMap<String, Set<IWcsObserver>>();

    public WcsObservable(){
    }

    /**
     * Add listners
     */
    public synchronized void addListener(IWcsObserver o){
        if (null == o){
            throw new NullPointerException("Can not add a null Observer");
        }

        String topic = o.getTopic();
        if (! listeners.containsKey(topic)){
            Set<IWcsObserver> listnerSet = new HashSet<IWcsObserver>();
            listnerSet.add(o);
            listeners.put(topic, listnerSet);
        } else {
            listeners.get(topic).add(o);
        }
    }

    /**
     * Remove an observer from the listener list.
     * @param o
     */
    public synchronized void removeListener(IWcsObserver o){
        if (null == o){
            return;
        }

        String topic = o.getTopic();
        if (! listeners.containsKey(topic)){
            logger.warn("Can not delete observer with topic: {}, no such listener", topic);
            return;
        }
        listeners.get(topic).remove(o);
    }

    /**
     * Notify observers when something happens
     */
    public void notifyListner(String topic, Object arg){
        if (! listeners.containsKey(topic)){
            logger.warn("There is no listeners for topic: {}, ignore", topic);
            return;
        }
        Set<IWcsObserver> observers = listeners.get(topic);
        Iterator<IWcsObserver> iter = observers.iterator();
        while (((Iterator) iter).hasNext()){
            iter.next().onMessage(this, arg);
        }
    }


}
