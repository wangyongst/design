package com.myweb.vo;

import com.myweb.pojo.AdminUser;
import com.utils.Result;

import java.util.List;

public class ResultUtils {

    public static Result result(Result result) {
        return result;
    }

    public static Object data(Result result){
        return result.getData();
    }
}
