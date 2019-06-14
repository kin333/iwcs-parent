package com.wisdom.iwcs.common.utils;

import java.util.HashMap;

/**
 * Created by devin on 17-1-6.
 */
public class GridChildPageRequest extends GridPageRequest {
    private HashMap<String, Object> postData;

    public HashMap<String, Object> getPostData() {
        return postData;
    }

    public void setPostData(HashMap<String, Object> postData) {
        this.postData = postData;
    }
}
