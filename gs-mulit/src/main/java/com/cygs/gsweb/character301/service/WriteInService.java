package com.cygs.gsweb.character301.service;

import com.cygs.gsweb.character301.dto.Gacha;
import com.cygs.gsweb.character301.entity.PickupEntity;

import java.util.HashMap;
import java.util.List;

public interface WriteInService {

    List<Gacha> getData(String uid);
    int add301Data(List<Gacha> gachaList);
    int add302Data(List<Gacha> gachaList);
    int add200Data(List<Gacha> gachaList);
    Integer writeIn(String url) throws Exception;
}
