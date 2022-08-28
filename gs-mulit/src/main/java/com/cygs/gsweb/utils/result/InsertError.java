package com.cygs.gsweb.utils.result;

import java.util.HashMap;
import java.util.List;

public class InsertError {

    private HashMap<Integer, String> errorMap = new HashMap<Integer, String>(){{
       put(0,"空数据");
       put(-1,"已过期");
       put(-2,"服务器超时，一定是米哈游干的，乆乆乆");
    }};

    public InsertError(){

    }

    public String getInsertError(Integer integer){
        if(errorMap.containsKey(integer)){
            return errorMap.get(integer);
        }
        return "未知错误";
    }

}
