package com.myweb.vo;

import com.myweb.pojo.User;
import com.utils.Result;

import java.util.List;

public class ResultUtils {

    public static Result userResult(Result result) {
        ((User) result.getData()).getRefer().setRefer(null);
        return result;
    }

    public static Result userListResult(Result result) {
        for (int i = 0; i < ((List<User>) result.getData()).size(); i++) {
            ((List<User>) result.getData()).get(i).getRefer().setRefer(null);
        }
        return result;
    }
}
