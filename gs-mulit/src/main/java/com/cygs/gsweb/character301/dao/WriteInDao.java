package com.cygs.gsweb.character301.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cygs.gsweb.character301.dto.Gacha;
import com.cygs.gsweb.character301.entity.PickupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author 赛勒一点也不努力
 */
@Repository
@Mapper
public interface WriteInDao extends BaseMapper<Gacha> {
    List<Gacha> getData(String uid);
    int add301Data(List<Gacha> gachaList);
    int add302Data(List<Gacha> gachaList);
    int add200Data(List<Gacha> gachaList);
    Integer writeIn(String url) throws Exception;
}
