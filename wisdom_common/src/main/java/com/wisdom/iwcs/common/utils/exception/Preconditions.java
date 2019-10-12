package com.wisdom.iwcs.common.utils.exception;

/**
 * Created by lin on 17-6-15.
 */
public final class Preconditions {

    public static void checkArgument(boolean expression, ApplicationErrorEnum QHErrorEnum) {
        if (!expression) {
            throw new BusinessException(QHErrorEnum);
        }
    }

    public static void checkArgument(boolean expression, String errMsg) {
        if (!expression) {
            throw new BusinessException(errMsg);
        }
    }

    public static void checkBusinessError(boolean error, String errorMsg) {
        if (error) {
            throw new BusinessException(errorMsg);
        }
    }

    public static <T> T checkNotNull(T reference, ApplicationErrorEnum QHErrorEnum) {
        if (reference == null) {
            throw new BusinessException(QHErrorEnum);
        } else {
            return reference;
        }
    }

    public static void checkState(boolean expression, ApplicationErrorEnum QHErrorEnum) {
        if (!expression) {
            throw new BusinessException(QHErrorEnum);
        }
    }

    public static void checkMesBusinessError(boolean error, String errorMsg, String reqCode) {
        if (error) {
            throw new MesBusinessException(reqCode, errorMsg);
        }
    }
    public static void checkMesBusinessError(boolean error, String errorMsg) {
        if (error) {
            throw new MesBusinessException(errorMsg);
        }
    }
}
