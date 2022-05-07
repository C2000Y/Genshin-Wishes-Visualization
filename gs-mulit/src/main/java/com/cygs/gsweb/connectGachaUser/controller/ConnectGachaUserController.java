package com.cygs.gsweb.connectGachaUser.controller;

import com.cygs.gsweb.character301.dto.PickupDTO;
import com.cygs.gsweb.character301.service.PickupService;
import com.cygs.gsweb.character301.service.WriteInService;
import com.cygs.gsweb.connectGachaUser.entity.ConnectGachaUserEntity;
import com.cygs.gsweb.connectGachaUser.service.ConnectGachaUserService;
import com.cygs.gsweb.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author Cyrus
 */
@RestController
@RequestMapping("uid")
public class ConnectGachaUserController implements Serializable {

    @Autowired
    ConnectGachaUserService connectGachaUserService;

    /**
     * localhost:6480/uid/list
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("list")
//    @ResponseBody
    public Result<List<ConnectGachaUserEntity>> list() throws Exception {
        List<ConnectGachaUserEntity> connectGachaUserEntityList = connectGachaUserService.getUids();
        return new Result<List<ConnectGachaUserEntity>>().ok(connectGachaUserEntityList);
    }

    @CrossOrigin
    @GetMapping("rewriteName")
    public Result<Integer> updateName(@RequestParam HashMap map)throws Exception {
        connectGachaUserService.rewriteName(map);
        return new Result<Integer>().ok(0);
    }

    @CrossOrigin
    @GetMapping("getName")
    public Result<String> getName(@RequestParam("uid") Integer uid)throws Exception {
        String name = connectGachaUserService.getNameByUid(uid);
        return new Result<String>().ok(name);
    }

}
