package com.cygs.gsweb.character301.dto;

import java.util.Date;

public class ListJson {
    private String uid;
    private String gacha_type;
    private String item_id;
    private String count;
    private Date time;
    private String name;
    private String lang;
    private String item_type;
    private String rank_type;
    private String id;

    public ListJson(String uid, String gacha_type, String item_id, String count, Date time, String name, String lang, String item_type, String rank_type, String id) {
        this.uid = uid;
        this.gacha_type = gacha_type;
        this.item_id = item_id;
        this.count = count;
        this.time = time;
        this.name = name;
        this.lang = lang;
        this.item_type = item_type;
        this.rank_type = rank_type;
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGacha_type() {
        return gacha_type;
    }

    public void setGacha_type(String gacha_type) {
        this.gacha_type = gacha_type;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getRank_type() {
        return rank_type;
    }

    public void setRank_type(String rank_type) {
        this.rank_type = rank_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "List{" +
                "connectGachaUser='" + uid + '\'' +
                ", gacha_type='" + gacha_type + '\'' +
                ", item_id='" + item_id + '\'' +
                ", count='" + count + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", lang='" + lang + '\'' +
                ", item_type='" + item_type + '\'' +
                ", rank_type='" + rank_type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
