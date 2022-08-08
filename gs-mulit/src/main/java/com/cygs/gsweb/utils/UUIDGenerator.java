package com.cygs.gsweb.utils;

import java.util.UUID;

public class UUIDGenerator {
    public static void main(String[] args) {
//        System.out.println(getUUID());

    }

    public static String getUUID(int length){
        UUID uuid = UUID.randomUUID();
        String result = uuid.toString().replaceAll("-","");
        result = result.substring(0,length);
        return result;
    }
}
