package com.wisdom.iwcs.service.task.subtask.intf;

public interface IWcsObserver {
    /**
     * When the event happens, this method will be notified.
     * @param o
     * @param arg
     */
    void onMessage(WcsObservable o, Object arg);

}
