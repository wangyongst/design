package com.myweb.vo;

import com.myweb.pojo.Follow;
import com.myweb.pojo.Help;
import com.myweb.pojo.Message;
import com.myweb.pojo.User;
import com.utils.Result;

import java.util.List;

public class ResultUtils {

    public static Result result(Result result) {
        if (User.class.isInstance(result.getData())) {
            ((User) result.getData()).getRefer().setRefer(null);
        } else if (Help.class.isInstance(result.getData())) {
            ((Help) result.getData()).getUser().setRefer(null);
        } else if (Follow.class.isInstance(result.getData())) {
            ((Follow) result.getData()).getUser().setRefer(null);
            ((Follow) result.getData()).getTouser().setRefer(null);
        } else if (Message.class.isInstance(result.getData())) {
            ((Message) result.getData()).getUser().setRefer(null);
            ((Message) result.getData()).getTouser().setRefer(null);
        } else if (List.class.isInstance(result.getData()) && !((List) result.getData()).isEmpty()) {
            if (((List) result.getData()).get(0) instanceof User) {
                for (int i = 0; i < ((List<User>) result.getData()).size(); i++) {
                    if (((List<User>) result.getData()).get(i).getRefer() != null) {
                        ((List<User>) result.getData()).get(i).getRefer().setRefer(null);
                    }
                }
            } else if (((List) result.getData()).get(0) instanceof Help) {
                for (int i = 0; i < ((List<Help>) result.getData()).size(); i++) {
                    if (((List<Help>) result.getData()).get(i).getUser() != null)
                        ((List<Help>) result.getData()).get(i).getUser().setRefer(null);
                }
            } else if (((List) result.getData()).get(0) instanceof Message) {
                for (int i = 0; i < ((List<Message>) result.getData()).size(); i++) {
                    ((List<Message>) result.getData()).get(i).getUser().setRefer(null);
                    ((List<Message>) result.getData()).get(i).getTouser().setRefer(null);
                }
            } else if (((List) result.getData()).get(0) instanceof Follow) {
                for (int i = 0; i < ((List<Follow>) result.getData()).size(); i++) {
                    ((List<Follow>) result.getData()).get(i).getUser().setRefer(null);
                    ((List<Follow>) result.getData()).get(i).getTouser().setRefer(null);
                }
            }
        }
        return result;
    }
}
