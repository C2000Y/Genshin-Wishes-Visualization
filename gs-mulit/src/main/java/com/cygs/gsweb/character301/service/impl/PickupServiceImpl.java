package com.cygs.gsweb.character301.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cygs.gsweb.character301.dao.PickupDao;
import com.cygs.gsweb.character301.dto.*;
import com.cygs.gsweb.character301.entity.PickupEntity;
import com.cygs.gsweb.character301.service.PickupService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cygs.gsweb.utils.summon.HTMLPageParser.convertUrl;

/**
 * @author 赛勒一点也不努力
 */
@Service
public class PickupServiceImpl implements PickupService{
    @Autowired
    PickupDao pickupDao;

    @Override
    public List<PickupEntity> getPickupByConditions(HashMap map){
        QueryWrapper<PickupEntity> wrapper = new QueryWrapper<>();
        String uid = (String)map.get("connectGachaUser");
        String rankType = (String)map.get("rankType");
        String name = (String)map.get("name");
        String itemType = (String)map.get("itemType");

        wrapper.eq(StringUtils.isNotBlank(uid), "connectGachaUser", uid);
        wrapper.eq(StringUtils.isNotBlank(rankType), "rank_type", rankType);
        wrapper.eq(StringUtils.isNotBlank(name), "name", name);
        wrapper.eq(StringUtils.isNotBlank(itemType), "item_type", itemType);


        List<PickupEntity> pickups = pickupDao.selectList(wrapper);
        return pickups;
    }

    @Override
    public Integer get301CountById(Integer uid) {
        return pickupDao.get301CountById(uid);
    }

    @Override
    public Integer get302CountById(Integer uid) {
        return pickupDao.get302CountById(uid);
    }

    @Override
    public Integer get200CountById(Integer uid) {
        return pickupDao.get200CountById(uid);
    }

    @Override
    public Integer getWishSum() {
        return null;
    }

    @Override
    public List<PickupDTO> getSummonCountByUid(HashMap map) {
        return pickupDao.getSummonCountByUid(map);
    }

    @Override
    public List<PickupDTO> getItemsByLevel(HashMap map) {
        return pickupDao.getItemsByLevel(map);
    }

    @Override
    public List<TotalPickupDTO> getTotalSummonCountByUid(Integer uid) {
        return pickupDao.getTotalSummonCountByUid(uid);
    }

    @Override
    public List<TotalPickupDTO> getPickUpTimesForAllByUid(Integer uid) {
        return pickupDao.getPickUpTimesForAllByUid(uid);
    }

    @Override
    public List<TotalPickupDTO> getPickUpTimesForCodeByUid(Integer uid, String code) {
        return pickupDao.getPickUpTimesForCodeByUid(uid, code);
    }

    //    for total count data 总计祈愿的数据
    @Override
    public List<PickupDTO> getLastCountForAllByUid(Integer uid) {
        return pickupDao.getLastCountForAllByUid(uid);
    }

//    for total count data 总计祈愿的数据
    @Override
    public List<PickupDTO> getCountForAllByUid(Integer uid) {
        return pickupDao.getCountForAllByUid(uid);
    }

//   Calculate total count data 总计祈愿的数据
    public List<TotalWishStatDTO> GetTotalCount(Integer uid) {
        List<PickupDTO> lastCountList = pickupDao.getLastCountForAllByUid(uid);
        List<PickupDTO> totalCountList = pickupDao.getCountForAllByUid(uid);
        System.out.println(lastCountList);
        System.out.println(totalCountList);
        List<TotalWishStatDTO> totalWishList = new ArrayList<TotalWishStatDTO>();
        int total = 0;
        for (int i = 0; i < lastCountList.size(); i++) {
            total += totalCountList.get(i).getCount();
        }
        totalWishList.add(new TotalWishStatDTO("All", total, 0));
        for (int i = 0; i < lastCountList.size(); i++) {
            TotalWishStatDTO tmp = new TotalWishStatDTO();
            tmp.setType(String.valueOf(i));
            tmp.setCount(totalCountList.get(i).getCount());
            if ((i == 1)) {
                tmp.setLeft(80 - totalCountList.get(i).getCount() + lastCountList.get(i).getCount());
            } else {
                tmp.setLeft(90 - totalCountList.get(i).getCount() + lastCountList.get(i).getCount());
            }
            totalWishList.add(tmp);
        }
        return totalWishList;
    }

    @Override
    public List<PickupEntity> list() {
        return pickupDao.list();
    }
}
