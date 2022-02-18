package com.cygs.gsweb.character301.controller;

import com.cygs.gsweb.character301.dto.PickupDTO;
import com.cygs.gsweb.character301.entity.PickupEntity;
import com.cygs.gsweb.character301.service.PickupService;
import com.cygs.gsweb.character301.service.WriteInService;
import com.cygs.gsweb.connectGachaUser.service.ConnectGachaUserService;
import com.cygs.gsweb.utils.result.InsertError;
import com.cygs.gsweb.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cyrus
 */
@RestController
@RequestMapping("summon")
public class SummonRecordController implements Serializable {

    @Autowired
    WriteInService writeInService;
    @Autowired
    PickupService pickupService;
    @Autowired
    ConnectGachaUserService connectGachaUserService;

    /**
     * localhost:6480/summon/pickup?connectGachaUser=100105164&rankType=4&itemType=角色
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("writeIn")
//    @ResponseBody
    public Result<Integer> writeIn(@RequestParam("url") String url) throws Exception {
        Integer uid = writeInService.writeIn(url);
        if(uid > 0){
            connectGachaUserService.insertUid(uid);
            return new Result<Integer>().ok(uid);
        } else {
            return new Result<Integer>().error(new InsertError().getInsertError(uid));
        }
    }

//    各池子一共抽了多少次
    @CrossOrigin
    @GetMapping("totalCount")
//    @ResponseBody
    public Result<HashMap> totalCount(@RequestParam("uid") Integer uid) throws Exception {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("standard", pickupService.get200CountById(uid));
        hashMap.put("character", pickupService.get301CountById(uid));
        hashMap.put("weapon", pickupService.get302CountById(uid));
        return new Result<HashMap>().ok(hashMap);
    }

//    抽到5星所花费的次数
//    localhost:6480/summon/SummonCount?connectGachaUser=100105164
    @CrossOrigin
    @GetMapping("SummonCount")
//    @ResponseBody
    public Result<List<PickupDTO>> summonCount(@RequestParam HashMap map) throws Exception {
        List<PickupDTO> pickupDTOList = pickupService.getSummonCountByUid(map);
        return new Result<List<PickupDTO>>().ok(pickupDTOList);
    }

//    星级分类
//    localhost:6480/summon/rankType?uid=100105164&code=301
    @CrossOrigin
    @GetMapping("rankType")
//    @ResponseBody
    public Result<List<PickupDTO>> summonLevel(@RequestParam HashMap map) throws Exception {
        List<PickupDTO> pickupDTOList = pickupService.getItemsByLevel(map);
        return new Result<List<PickupDTO>>().ok(pickupDTOList);
    }

}
