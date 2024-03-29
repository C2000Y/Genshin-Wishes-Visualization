package com.cygs.gsweb.connectGachaUser.service;

import com.cygs.gsweb.character301.dto.PickupDTO;
import com.cygs.gsweb.character301.entity.PickupEntity;
import com.cygs.gsweb.connectGachaUser.entity.ConnectGachaUserEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface ConnectGachaUserService {
    List<ConnectGachaUserEntity> getUids();
    void rewriteName(HashMap map);
    void insertUid(Integer uid);
    String getNameByUid(Integer uid);
    Long getLastUpdateByUid(Integer uid);
}
