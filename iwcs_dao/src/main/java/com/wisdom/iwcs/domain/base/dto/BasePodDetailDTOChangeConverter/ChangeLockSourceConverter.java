package com.wisdom.iwcs.domain.base.dto.BasePodDetailDTOChangeConverter;

import com.github.crab2died.converter.WriteConvertible;

public class ChangeLockSourceConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object){
        if (object!=null){
            String s=(String)object;
            if(s.equals("manual_lock")){
                return "手动锁";
            }else{
                return "自动锁";
            }
        }
        else{
            return "";
        }
    }
}
