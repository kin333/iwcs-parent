package com.wisdom.iwcs.domain.base.dto.MainTaskDTOChangeConverter;

import com.github.crab2died.converter.WriteConvertible;

public class ChangeTaskStatusConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object){
        if (object!=null){
            String s=(String) object;
            if(s.equals("1")){
                return "执行中";
            }else if(s.equals("0")){
                return "已创建";
            }else{
                return "已结束";
            }
        }else{
            return "";
        }
    }
}
