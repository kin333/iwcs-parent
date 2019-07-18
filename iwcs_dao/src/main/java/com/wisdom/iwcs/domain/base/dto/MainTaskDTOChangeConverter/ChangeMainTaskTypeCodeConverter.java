package com.wisdom.iwcs.domain.base.dto.MainTaskDTOChangeConverter;

import com.github.crab2died.converter.WriteConvertible;

public class ChangeMainTaskTypeCodeConverter implements WriteConvertible{
    @Override
    public Object execWrite(Object object){
        if (object!=null){
            String s=(String)object;
            if(s.equals("plAutoWbCallPod")){
                return "工作台自动点位呼叫空货架";
            }else if(s.equals("agingToQuaInsp")){
                return "老化区前往检验点";
            }else if(s.equals("plBufSupply")){
                return "自动补充产线空货架缓存区";
            }else if(s.equals("plToAging")){
                return "产线去老化区搬运";
            }else if(s.equals("quaBufToQua")){
                return "自动检验缓冲区去检验点";
            }else if(s.equals("quaInspToElvBuf")){
                return "";
            }else if(s.equals("pTopWithoutPodCheck")){
                return "点到点";
            }else{
                return "点到点";
            }
        }else{
            return "";
        }
    }
}
