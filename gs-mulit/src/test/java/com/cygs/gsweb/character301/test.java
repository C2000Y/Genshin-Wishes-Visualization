package com.cygs.gsweb.character301;

import com.cygs.gsweb.character301.dao.PickupDao;
import com.cygs.gsweb.character301.entity.PickupEntity;
import com.cygs.gsweb.connectGachaUser.service.ConnectGachaUserService;
import com.cygs.gsweb.fileReader.entity.FileReaderEntity;
import com.cygs.gsweb.fileReader.service.FileReaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class test {

    @Autowired
    private PickupDao pickupDao;
    private ConnectGachaUserService connectGachaUserService;
    @Autowired
    private FileReaderService fileReaderService;

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

    @Test
    public void updateName(){
        HashMap map = new HashMap<>();
        map.put("name","a");
        map.put("uid",111);
        connectGachaUserService.rewriteName(map);
    }

    @Test
    public void getDict(){
        HashMap map = new HashMap<>();
        map.put("id",3);
        map.put("name", "丽莎");
        map.put("url", "https://patchwiki.biligame.com/images/ys/8/83/boxpoaln8hyivr555agt2371y3ss8um.png");
        map.put("rank", "4");
        map.put("type", "角色");
        map.put("nameEn", "Lisa1");
//        List<FileReaderEntity> fileReaderEntities = fileReaderService.getDictionary();
//        for (FileReaderEntity fileReader: fileReaderEntities) {
//            map.put(fileReader.getName(), fileReader);
//        }
//        System.out.println(map);
        Integer a = fileReaderService.updateDictionaryById(map);
        System.out.println(a);
    }

}
