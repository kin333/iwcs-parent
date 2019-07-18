package com.wisdom.iwcs.domain.base.dto.BasePodDetailDTOChangeConverter;

import com.github.crab2died.converter.WriteConvertible;

public class ChangeInLockConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object){
        if (object!=null){
            Integer s=(Integer)object;
            if(s.equals("1")){
                return "是";
            }else{
                return "否";
            }
        }else{
            return "";
        }
    }
}
