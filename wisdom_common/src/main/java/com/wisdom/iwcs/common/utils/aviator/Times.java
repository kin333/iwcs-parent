package com.wisdom.iwcs.common.utils.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

/**
 * Created by edwin on 17/01/2017.
 */
public class Times extends AbstractVariadicFunction {

    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        if (args != null && args.length >= 3) {
            double value1 = AviatorEvaluator.execute(FunctionUtils.getStringValue(args[0], env)) instanceof AviatorString ?
                    0.0 : Double.parseDouble(AviatorEvaluator.execute(FunctionUtils.getStringValue(args[0], env)).toString());
            //房间个数
            int numberOfRoom = FunctionUtils.getNumberValue(args[0], env).intValue();
            //传入数据的格式必须依次为各个房间的长宽 [长，宽...]
            if (args.length != 1 + 2 * numberOfRoom) {
                return new AviatorString("");
            }

//            for

            return new AviatorDouble(FunctionUtils.getNumberValue(args[1], env).intValue() * value1);
        }
        return new AviatorString("");
    }


    @Override
    public String getName() {
        return "times";
    }

}