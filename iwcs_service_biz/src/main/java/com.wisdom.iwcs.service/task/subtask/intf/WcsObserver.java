package com.wisdom.iwcs.service.task.subtask.intf;

public interface WcsObserver {
    /**
     * When the event happens, this method will be notified.
     * @param o
     * @param arg
     */
    void onMessage(WcsObservable o, Object arg);

    /**
     * Identify which topic this observer interested in, the message center will only push that kind of messages.
     * @return
     */
    String getTopic();

}
