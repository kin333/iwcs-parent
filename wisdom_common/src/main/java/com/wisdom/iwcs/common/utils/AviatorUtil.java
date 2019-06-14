package com.wisdom.iwcs.common.utils;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDecimal;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Edwin on 2017/2/13.
 */
public class AviatorUtil {

    public BigDecimal calc(String expressionStr, Map<String, Object> env) {
        TraversalFunction traversalFunction = new TraversalFunction();
        MaxFunction maxFunction = new MaxFunction();
        MinFunction minFunction = new MinFunction();
        RoundUpFunction roundUpFunction = new RoundUpFunction();
        RoundCeilingFunction roundCeilingFunction = new RoundCeilingFunction();
        RoundFloorFunction roundFloorFunction = new RoundFloorFunction();
        CeilCoefficientFunction ceilCoefficientFunction = new CeilCoefficientFunction();
        if (!AviatorEvaluator.containsFunction("遍历"))
            AviatorEvaluator.addFunction(traversalFunction);
        if (!AviatorEvaluator.containsFunction("取最大值"))
            AviatorEvaluator.addFunction(maxFunction);
        if (!AviatorEvaluator.containsFunction("取最小值"))
            AviatorEvaluator.addFunction(minFunction);
        if (!AviatorEvaluator.containsFunction("四舍五入"))
            AviatorEvaluator.addFunction(roundUpFunction);
        if (!AviatorEvaluator.containsFunction("向上取整"))
            AviatorEvaluator.addFunction(roundCeilingFunction);
        if (!AviatorEvaluator.containsFunction("向下取整"))
            AviatorEvaluator.addFunction(roundFloorFunction);
        if (!AviatorEvaluator.containsFunction("系数取整"))
            AviatorEvaluator.addFunction(ceilCoefficientFunction);

        String expression = expressionStr.replaceAll("\\$", "");

        //        System.out.println(expressionStr);

        Expression compiledExp = AviatorEvaluator.compile(expression);
        BigDecimal result = (BigDecimal) compiledExp.execute(env);
        return result;
    }

    /**
     * 生成公式字符串
     *
     * @param inputStr
     * @param env
     * @return
     */
    public String buildFormula(String inputStr, Map<String, Object> env) {

        String[] splitStr = inputStr.split("\\$");
        String calcStr = "";
        if (splitStr.length > 1) {
            for (int i = 0; i < splitStr.length; i++) {    // 有函数
                String[] splitItemStr = splitStr[i].split("'");
                if (splitItemStr.length > 1) {
                    List<Map<String, Object>> mapList = (List<Map<String, Object>>) env.get(splitItemStr[3]);
                    if (null == mapList) {
                        splitStr[i] = "0";
                    } else {
                        String str = "";
                        char listIndex = 'A';
                        for (Map<String, Object> forMap : mapList) {
                            String itemStr = splitItemStr[1];
                            for (String key : forMap.keySet()) {
                                itemStr = itemStr.replaceAll(key, key + listIndex + "[" + forMap.get(key) + "]");
                            }
                            listIndex++;
                            str += "(" + itemStr + ")+";
                        }
                        str = str.substring(0, str.length() - 1);
                        str = "(" + str + ")";
                        splitStr[i] = str;
                    }

                } else {  //  无函数
                    for (String key : env.keySet()) {
                        if (!(env.get(key) instanceof List)) {
                            splitStr[i] = splitStr[i].replaceAll(key, key + "[" + env.get(key) + "]");
                        }
                    }
                }
                calcStr += splitStr[i];
            }
        } else {
            calcStr = inputStr;
            for (String key : env.keySet()) {
                calcStr = calcStr.replaceAll(key, key + "[" + env.get(key) + "]");
            }

        }
        return calcStr;

    }

}


class TraversalFunction extends AbstractFunction {

    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        AviatorString aviatorString = (AviatorString) arg1;
        AviatorString aviator2String = (AviatorString) arg2;
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) env.get(aviator2String.getLexeme());
        BigDecimal sum = new BigDecimal(0);
        if (null == mapList) {
            return new AviatorDecimal(sum);
        }
        for (Map<String, Object> map : mapList) {
            String expression = aviatorString.getLexeme();
            Expression compiledExp = AviatorEvaluator.compile(expression);
            System.out.println(expression);
            BigDecimal result = (BigDecimal) compiledExp.execute(map);
            sum = sum.add(result);
        }
        return new AviatorDecimal(sum);
    }

    @Override
    public String getName() {
        return "遍历";
    }
}

class MaxFunction extends AbstractVariadicFunction {

    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        if (args != null) {
            Number minValue = FunctionUtils.getNumberValue(args[0], env);
            AviatorObject returnObject = args[0];
            for (AviatorObject arg : args) {
                Number index = FunctionUtils.getNumberValue(arg, env);
                if (arg.getValue(env) != null) {
                    if (index.doubleValue() > minValue.doubleValue()) {
                        minValue = index;
                        returnObject = arg;
                    }
                }
            }
            return returnObject;
        }
        return new AviatorString(null);
    }


    @Override
    public String getName() {
        return "取最大值";
    }

}

class MinFunction extends AbstractVariadicFunction {

    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        if (args != null) {
            Number minValue = FunctionUtils.getNumberValue(args[0], env);
            AviatorObject returnObject = args[0];
            for (AviatorObject arg : args) {
                Number index = FunctionUtils.getNumberValue(arg, env);
                if (arg.getValue(env) != null) {
                    if (index.doubleValue() < minValue.doubleValue()) {
                        minValue = index;
                        returnObject = arg;
                    }
                }
            }
            return returnObject;
        }
        return new AviatorString(null);
    }


    @Override
    public String getName() {
        return "取最小值";
    }

}


class RoundUpFunction extends AbstractFunction {

    public AviatorObject call(Map<String, Object> env, AviatorObject arg) {
        AviatorDecimal returnDecimal = (AviatorDecimal) arg;
        BigDecimal returnBigDecimal = returnDecimal.toDecimal();
        returnBigDecimal = returnBigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP);
        return new AviatorDecimal(returnBigDecimal);
    }

    @Override
    public String getName() {
        return "四舍五入";
    }
}


class RoundCeilingFunction extends AbstractFunction {

    public AviatorObject call(Map<String, Object> env, AviatorObject arg) {
        AviatorDecimal returnDecimal = (AviatorDecimal) arg;
        BigDecimal returnBigDecimal = returnDecimal.toDecimal();
        returnBigDecimal = returnBigDecimal.setScale(0, BigDecimal.ROUND_CEILING);
        return new AviatorDecimal(returnBigDecimal);
    }

    @Override
    public String getName() {
        return "向上取整";
    }
}


class RoundFloorFunction extends AbstractFunction {

    public AviatorObject call(Map<String, Object> env, AviatorObject arg) {
        AviatorDecimal returnDecimal = (AviatorDecimal) arg;
        BigDecimal returnBigDecimal = returnDecimal.toDecimal();
        returnBigDecimal = returnBigDecimal.setScale(0, BigDecimal.ROUND_FLOOR);
        return new AviatorDecimal(returnBigDecimal);
    }

    @Override
    public String getName() {
        return "向下取整";
    }
}


class CeilCoefficientFunction extends AbstractFunction {

    public AviatorObject call(Map<String, Object> env, AviatorObject arg, AviatorObject ceilCoefficient) {
        AviatorDecimal returnDecimal = (AviatorDecimal) arg;
        AviatorDecimal ceilCoefficientDecimal = (AviatorDecimal) ceilCoefficient;
        BigDecimal returnBigDecimal = returnDecimal.toDecimal();
        BigDecimal ceilCoefficientBigDecimal = ceilCoefficientDecimal.toDecimal();
        if (returnBigDecimal.compareTo(new BigDecimal(0)) < 0) {
            if (returnBigDecimal.divideAndRemainder(new BigDecimal(1))[1].abs().compareTo(ceilCoefficientBigDecimal) < 0) {
                returnBigDecimal = returnBigDecimal.setScale(0, BigDecimal.ROUND_CEILING);
            } else {
                returnBigDecimal = returnBigDecimal.setScale(0, BigDecimal.ROUND_FLOOR);
            }
        } else {
            if (returnBigDecimal.divideAndRemainder(new BigDecimal(1))[1].compareTo(ceilCoefficientBigDecimal) < 0) {
                returnBigDecimal = returnBigDecimal.setScale(0, BigDecimal.ROUND_FLOOR);
            } else {
                returnBigDecimal = returnBigDecimal.setScale(0, BigDecimal.ROUND_CEILING);
            }
        }

        return new AviatorDecimal(returnBigDecimal);
    }

    @Override
    public String getName() {
        return "系数取整";
    }
}


