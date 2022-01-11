package com.cygs.gsweb.character301.dto;

public class DataJson {
    private Integer retcode;
    private String message;
    private Data data;

    public DataJson(Integer retcode, String message, Data data) {
        this.retcode = retcode;
        this.message = message;
        this.data = data;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataJson{" +
                "retcode=" + retcode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
