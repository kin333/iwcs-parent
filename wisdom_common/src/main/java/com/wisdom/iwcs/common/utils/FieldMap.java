package com.wisdom.iwcs.common.utils;

import java.util.HashMap;

/**
 * Created by devin on 17-1-18.
 */
public class FieldMap {

    public static HashMap<String, String> fieldMap = new HashMap<String, String>() {
        {
            put("roleName", "role_name");
            put("lastModifiedName", "last_modified_name");
            put("lastModifiedTimeString", "last_modified_time");
            put("dictType", "dict_type");
            put("dictTypeName", "dict_type_name");
            put("dictName", "dict_name");
            put("dictValue", "dict_value");
            put("statusStr", "status");
            put("createdByStr", "created_by_str");
            put("createdTimeStr", "created_time");
            put("lastModifiedByStr", "last_modified_by_str");
            put("lastModifiedTimeStr", "last_modified_time");
        }
    };
}
