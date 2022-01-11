package com.cygs.gsweb.character301.dto;

import java.util.Date;

public class Gacha {

    private String id;
    private String uid;
    private String name;
    private String itemType;
    private String rankType;
    private Date time;

    public Gacha(String id, String uid, String name, String itemType, String rankType, Date time) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.itemType = itemType;
        this.rankType = rankType;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getRankType() {
        return rankType;
    }

    public void setRankType(String rankType) {
        this.rankType = rankType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Gacha{" +
                "id='" + id + '\'' +
                ", connectGachaUser='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", itemType='" + itemType + '\'' +
                ", rankType='" + rankType + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
