package com.cygs.gsweb.character301.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cygs.gsweb.character301.dao.PickupDao;
import com.cygs.gsweb.character301.dao.WriteInDao;
import com.cygs.gsweb.character301.dto.DataJson;
import com.cygs.gsweb.character301.dto.Gacha;
import com.cygs.gsweb.character301.dto.ListJson;
import com.cygs.gsweb.character301.entity.PickupEntity;
import com.cygs.gsweb.character301.service.PickupService;
import com.cygs.gsweb.character301.service.WriteInService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.cygs.gsweb.utils.summon.HTMLPageParser.convertUrl;
import static com.cygs.gsweb.utils.summon.HTMLPageParser.getSummon;

/**
 * @author 赛勒一点也不努力
 */
@Service
public class WriteInServiceImpl implements WriteInService {
    @Autowired
    WriteInDao writeInDao;
    Integer uid;

    @Override
    public List<Gacha> getData(String uid) {
        return null;
    }

    @Override
    public int add301Data(List<Gacha> gachaList) {
        return 0;
    }

    @Override
    public int add302Data(List<Gacha> gachaList) {
        return 0;
    }

    @Override
    public int add200Data(List<Gacha> gachaList) {
        return 0;
    }

    @Override
    // TODO: 文件验证
    public Integer writeIn(String url) throws Exception {
        System.out.println(url);
        System.out.println("开始读取...");
        Integer res = insertSummon(getSummon(convertUrl(url,"200")),"200");
        res = Math.max(res, insertSummon(getSummon(convertUrl(url,"301")),"301"));
        res = Math.max(res, insertSummon(getSummon(convertUrl(url,"302")),"302"));
        return res;
    }

    private Integer insertSummon(List<Gacha> gachaList, String type) throws Exception{
        if(!gachaList.isEmpty()) {
//        角色up
            if(type.compareTo("301") == 0){
                writeInDao.add301Data(gachaList);
            }
//        武器up
            else if(type.compareTo("302") == 0){
                writeInDao.add302Data(gachaList);
            }
//        标配
            else if(type.compareTo("200") == 0){
                writeInDao.add200Data(gachaList);
            }
            return Integer.parseInt(gachaList.get(0).getUid());
        } else{
            return 0;
        }
    }

}
