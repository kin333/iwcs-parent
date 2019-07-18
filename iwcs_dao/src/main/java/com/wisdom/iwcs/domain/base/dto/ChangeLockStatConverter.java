package com.wisdom.iwcs.domain.base.dto;

import com.github.crab2died.converter.WriteConvertible;

public class ChangeLockStatConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object){
        if (object!=null){
            Integer s=(Integer) object;
            if(s.equals(0)){
                return "无锁";
            }else{
                return "有锁";
            }
        }else{
            return "";
        }
    }
}
