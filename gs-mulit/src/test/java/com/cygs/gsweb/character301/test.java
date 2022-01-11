package com.cygs.gsweb.character301;

import com.cygs.gsweb.character301.dao.PickupDao;
import com.cygs.gsweb.character301.entity.PickupEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class test {

    @Autowired
    private PickupDao pickupDao;

    @Test
    void listAll() {
        List<PickupEntity> pickupEntityList = pickupDao.selectList(null);
        pickupEntityList.forEach(System.out::println);
    }

    @Test
    public void testSelectByConditions() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("item_type", "角色");
        map.put("connectGachaUser", "100105164");
        map.put("rank_type","1");

        List<PickupEntity> users = pickupDao.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testList() {
        List<PickupEntity> pickupEntityList = pickupDao.list();
        pickupEntityList.forEach(System.out::println);
    }

    @Test
    public void getCountById() {
        Integer integer32 = pickupDao.get302CountById(100105164);
        Integer integer31 = pickupDao.get301CountById(100105164);
        Integer integer20 = pickupDao.get200CountById(100105164);
//        System.out.println(integer);
    }
}
