package com.wisdom.iwcs.common.utils.aviator;

import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

/**
 * Created by edwin on 17/01/2017.
 */
public class Ceil extends AbstractVariadicFunction {

    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        if (args != null) {
            Number longth = FunctionUtils.getNumberValue(args[0], env);
            Number width = FunctionUtils.getNumberValue(args[1], env);

            return new AviatorDouble(Math.ceil(longth.doubleValue()) * Math.ceil(width.doubleValue()));
        }
        return new AviatorString("");
    }


    @Override
    public String getName() {
        return "ceil";
    }

}
