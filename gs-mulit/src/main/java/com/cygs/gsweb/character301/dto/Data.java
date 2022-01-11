package com.cygs.gsweb.character301.dto;

import java.util.List;

public class Data {
    private String page;
    private String size;
    private String total;
    private List<ListJson> list;

    public Data(String page, String size, String total, List<ListJson> list) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.list = list;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ListJson> getList() {
        return list;
    }

    public void setList(List<ListJson> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Data{" +
                "page='" + page + '\'' +
                ", size='" + size + '\'' +
                ", total='" + total + '\'' +
                ", list='" + list + '\'' +
                '}';
    }
}
