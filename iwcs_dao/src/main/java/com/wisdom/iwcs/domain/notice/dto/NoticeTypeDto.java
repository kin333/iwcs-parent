package com.wisdom.iwcs.domain.notice.dto;

import java.util.List;

/**
 * Created by centling on 2018/5/19.
 */
public class NoticeTypeDto {

    private List<NoticeDto> unreadList;
    private List<NoticeDto> readList;
    private List<NoticeDto> recycleList;

    public List<NoticeDto> getUnreadList() {
        return unreadList;
    }

    public void setUnreadList(List<NoticeDto> unreadList) {
        this.unreadList = unreadList;
    }

    public List<NoticeDto> getReadList() {
        return readList;
    }

    public void setReadList(List<NoticeDto> readList) {
        this.readList = readList;
    }

    public List<NoticeDto> getRecycleList() {
        return recycleList;
    }

    public void setRecycleList(List<NoticeDto> recycleList) {
        this.recycleList = recycleList;
    }
}
