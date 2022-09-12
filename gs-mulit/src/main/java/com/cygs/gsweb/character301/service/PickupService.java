package com.cygs.gsweb.character301.service;

import com.cygs.gsweb.character301.dto.PickupDTO;
import com.cygs.gsweb.character301.dto.TotalPickupDTO;
import com.cygs.gsweb.character301.dto.TotalWishStatDTO;
import com.cygs.gsweb.character301.entity.PickupEntity;

import java.util.HashMap;
import java.util.List;

public interface PickupService {

    List<PickupEntity> list();
    List<PickupEntity> getPickupByConditions(HashMap map);
    Integer get301CountById(Integer uid);
    Integer get302CountById(Integer uid);
    Integer get200CountById(Integer uid);
    Integer getWishSum();
    List<PickupDTO> getSummonCountByUid(HashMap map);
    List<PickupDTO> getItemsByLevel(HashMap map);
    List<TotalPickupDTO> getTotalSummonCountByUid(Integer uid);
    List<TotalPickupDTO> getPickUpTimesForAllByUid(Integer uid);
    List<TotalPickupDTO> getPickUpTimesForCodeByUid(Integer uid, String code);
    List<PickupDTO> getLastCountForAllByUid(Integer uid);
    List<PickupDTO> getCountForAllByUid(Integer uid);
    List<TotalWishStatDTO> GetTotalCount(Integer uid);
}
