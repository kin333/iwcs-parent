package com.wisdom.iwcs.domain.base.dto.SubTaskDTOChangeConverter;

import com.github.crab2died.converter.WriteConvertible;

public class ChangeSubTaksTypConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object object){
        if (object!=null){
            String s=(String)object;
            if(s.equals("hikp2p")){
                return "工作台自动点位呼叫空货架";
            }else if(s.equals("hikp2p_nopodcheck")){
                return "海康点到点(不校验起点货架)";
            }else if(s.equals("Tmp_p3p")){
                return "测试类型";
            }
            else{
                return "";
            }
        }else{
            return "";
        }
    }
}
