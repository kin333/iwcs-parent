package com.wisdom.iwcs.common.utils;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.function.CommonFunction;
import com.greenpineyu.fel.function.Function;

public class FelUtil {
    public static FelEngine E = new FelEngineImpl();
    public static Function CEIL = new CommonFunction() {
        public String getName() {
            return "CEIL";
        }

        @Override
        public Object call(Object[] arguments) {
            Object msg = null;
            if (arguments != null && arguments.length > 0) {
                msg = Math.ceil((double) arguments[0]);
            }
            return msg;
        }

    };
    public static Function FLOOR = new CommonFunction() {
        public String getName() {
            return "FLOOR";
        }

        @Override
        public Object call(Object[] arguments) {
            Object msg = null;
            if (arguments != null && arguments.length > 0) {
                msg = Math.floor((double) arguments[0]);
            }
            return msg;
        }

    };
    public static Function MAX = new CommonFunction() {
        public String getName() {
            return "MAX";
        }

        @Override
        public Object call(Object[] arguments) {
            Object msg = null;
            if (arguments != null && arguments.length > 0) {
                msg = Math.max((double) arguments[0], (double) arguments[1]);
            }
            return msg;
        }

    };
    public static Function TIMES = new CommonFunction() {
        public String getName() {
            return "TIMES";
        }

        @Override
        public Object call(Object[] arguments) {
            Object msg = null;
            if (arguments != null && arguments.length > 0) {
                for (int i = 0; i < (int) arguments[1]; i++) {
//	            	arguments[0];
                }
            }
            return msg;
        }

    };

    public static void FelExpression() {
        E.addFun(CEIL);
        E.addFun(FLOOR);
        E.addFun(MAX);
        E.addFun(TIMES);


    }

}
