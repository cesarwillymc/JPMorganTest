package com.cesarwillymc.jpmorgantest.domain.base;

import static com.cesarwillymc.jpmorgantest.util.constants.ConstantsKt.LOG_DOMAIN;
import android.util.Log;
import com.cesarwillymc.jpmorgantest.util.state.Result;

/**
 * Created by Cesar Canaza on 10/3/23.
 * cesarwilly.mc@gmail.com
 * <p>
 * IOWA, United States.
 * <p>
 * It can be used for use cases that return Unit, Boolean, Int, etc.
 * No mapper needed
 */
public abstract class SuspendNoParamsUseCase<Results> {

    public Result<Results> invoke() {
        try {
            return execute();
        } catch (Exception e) {
            Log.e(LOG_DOMAIN, e.getMessage());
            return new Result.Error.Error(e);
        }
    }

    protected abstract Result<Results> execute() throws Exception;
}