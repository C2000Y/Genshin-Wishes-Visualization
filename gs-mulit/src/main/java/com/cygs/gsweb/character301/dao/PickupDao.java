package com.cygs.gsweb.character301.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cygs.gsweb.character301.dto.PickupDTO;
import com.cygs.gsweb.character301.dto.TotalPickupDTO;
import com.cygs.gsweb.character301.entity.PickupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 赛勒一点也不努力
 */
@Repository
@Mapper
public interface PickupDao extends BaseMapper<PickupEntity> {
    List<PickupEntity> list();
    Integer get302CountById(Integer uid);
    Integer get301CountById(Integer uid);
    Integer get200CountById(Integer uid);
    Integer getWishSum();
    List<PickupDTO> getSummonCountByUid(HashMap map);
    List<PickupDTO> getItemsByLevel(HashMap map);
    List<TotalPickupDTO> getTotalSummonCountByUid(Integer uid);
    List<TotalPickupDTO> getPickUpTimesForAllByUid(Integer uid);
    List<TotalPickupDTO> getPickUpTimesForCodeByUid(Integer uid, String code);
    List<PickupDTO> getLastCountForAllByUid(Integer uid);
    List<PickupDTO> getCountForAllByUid(Integer uid);
}
