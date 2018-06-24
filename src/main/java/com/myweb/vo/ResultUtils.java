package com.myweb.vo;

import com.myweb.pojo.Message;
import com.myweb.pojo.User;
import com.utils.Result;

import java.util.List;

public class ResultUtils {

    public static Result userResult(Result result) {
        if (result.getStatus() == 1 && ((User) result.getData()).getRefer() != null)
            ((User) result.getData()).getRefer().setRefer(null);
        return result;
    }

    public static Result userListResult(Result result) {
        if (result.getStatus() == 1) {
            for (int i = 0; i < ((List<User>) result.getData()).size(); i++) {
                if (((List<User>) result.getData()).get(i).getRefer() != null)
                    ((List<User>) result.getData()).get(i).getRefer().setRefer(null);
            }
        }
        return result;
    }

    public static Result messageListResult(Result result) {
        if (result.getStatus() == 1) {
            for (int i = 0; i < ((List<Message>) result.getData()).size(); i++) {
                if (((List<Message>) result.getData()).get(i).getUser().getRefer() != null) {
                    ((List<Message>) result.getData()).get(i).getUser().getRefer().setRefer(null);
                }
                if (((List<Message>) result.getData()).get(i).getTouser().getRefer() != null) {
                    ((List<Message>) result.getData()).get(i).getTouser().getRefer().setRefer(null);
                }
            }
        }
        return result;
    }
}
