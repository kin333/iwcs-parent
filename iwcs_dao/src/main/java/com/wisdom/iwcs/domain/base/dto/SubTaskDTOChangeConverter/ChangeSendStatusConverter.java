package com.wisdom.iwcs.domain.base.dto.SubTaskDTOChangeConverter;

import com.github.crab2died.converter.WriteConvertible;

public class ChangeSendStatusConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object){
        if (object!=null){
            String s=(String)object;
            if(s.equals(0)){
                return "待下发";
            }else{
                return "已下发";
            }
        }else{
            return "";
        }
    }
}
