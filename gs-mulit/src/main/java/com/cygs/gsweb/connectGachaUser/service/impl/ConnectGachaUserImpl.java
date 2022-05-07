package com.cygs.gsweb.connectGachaUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cygs.gsweb.character301.dao.PickupDao;
import com.cygs.gsweb.character301.dto.PickupDTO;
import com.cygs.gsweb.character301.entity.PickupEntity;
import com.cygs.gsweb.character301.service.PickupService;
import com.cygs.gsweb.connectGachaUser.dao.ConnectGachaUserDao;
import com.cygs.gsweb.connectGachaUser.entity.ConnectGachaUserEntity;
import com.cygs.gsweb.connectGachaUser.service.ConnectGachaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 赛勒一点也不努力
 */
@Service
public class ConnectGachaUserImpl implements ConnectGachaUserService {

    @Autowired
    ConnectGachaUserDao connectGachaUserDao;

    @Override
    public List<ConnectGachaUserEntity> getUids() {
        return connectGachaUserDao.selectList(null);
    }

    @Override
    public void rewriteName(HashMap map) {
//        ConnectGachaUserEntity user = new ConnectGachaUserEntity(uid, name);
//        connectGachaUserDao.updateById(user);
        connectGachaUserDao.rewriteName(map);
    }

    @Override
    public void insertUid(Integer uid) {
        connectGachaUserDao.insertUid(uid);
    }

    @Override
    public String getNameByUid(Integer uid) {
        return connectGachaUserDao.getNameByUid(uid);
    }


}
