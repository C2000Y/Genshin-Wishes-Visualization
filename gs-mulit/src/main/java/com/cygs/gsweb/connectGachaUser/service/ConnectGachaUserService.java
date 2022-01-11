package com.cygs.gsweb.connectGachaUser.service;

import com.cygs.gsweb.character301.dto.PickupDTO;
import com.cygs.gsweb.character301.entity.PickupEntity;
import com.cygs.gsweb.connectGachaUser.entity.ConnectGachaUserEntity;

import java.util.HashMap;
import java.util.List;

public interface ConnectGachaUserService {
    List<ConnectGachaUserEntity> getUids();
    void insertUid(Integer uid);
}
