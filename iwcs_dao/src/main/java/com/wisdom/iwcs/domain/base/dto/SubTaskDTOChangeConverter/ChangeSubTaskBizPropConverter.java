package com.wisdom.iwcs.domain.base.dto.SubTaskDTOChangeConverter;

import com.github.crab2died.converter.WriteConvertible;

public class ChangeSubTaskBizPropConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object){
        if (object!=null){
            String s=(String)object;
            if(s.equals("autoAgingPosFirst")){
                return "自动区优先";
            }else if(s.equals("manualAgingPosFirst")){
                return "手动区优先";
            }else{
                return "";
            }
        }else{
            return "";
        }
    }
}
