package com.cygs.gsweb.utils.result;

import java.util.HashMap;
import java.util.List;

public class InsertError {

    private HashMap<Integer, String> errorMap = new HashMap<Integer, String>(){{
       put(0,"空数据");
       put(-1,"已过期");
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
