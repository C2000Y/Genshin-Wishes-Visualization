package com.cygs.gsweb.fileReader.controller;

import com.cygs.gsweb.character301.service.WriteInService;
import com.cygs.gsweb.connectGachaUser.entity.ConnectGachaUserEntity;
import com.cygs.gsweb.connectGachaUser.service.ConnectGachaUserService;
import com.cygs.gsweb.utils.result.InsertError;
import com.cygs.gsweb.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;

/**
 * @author Cyrus
 */
@RestController
@RequestMapping("file")
public class FileController implements Serializable {

    @Autowired
    WriteInService writeInService;
    @Autowired
    ConnectGachaUserService connectGachaUserService;

    /**
     * localhost:6480/uid/list
     * @param
     * @return
     */
    @CrossOrigin
    @RequestMapping("/reader")
    @ResponseBody
    public Result<Integer> getFile(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String lineTxt;
        System.out.println("before get");
        while ((lineTxt=bufferedReader.readLine())!=null){
            if(lineTxt.contains("#/log")){
                System.out.println(lineTxt);
                Integer uid = writeInService.writeIn(lineTxt);
                if(uid > 0){
                    connectGachaUserService.insertUid(uid);
                    return new Result<Integer>().ok(uid);
                } else {
                    return new Result<Integer>().error(new InsertError().getInsertError(uid));
                }
            }
        }
        return new Result<Integer>().error("文件有误");
    }

}
