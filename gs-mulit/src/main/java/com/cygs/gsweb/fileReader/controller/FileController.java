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
import java.net.URLDecoder;
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
     *
     * @param
     * @return
     */
    @CrossOrigin
    @PostMapping("/reader")
    public Result<Integer> getUrl(@RequestBody String url) throws Exception {
//        System.out.println(url);
//        System.out.println(url.getParameter("url"));
//        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//        MultipartFile file = multipartHttpServletRequest.getFile("file");
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
//        String lineTxt;
        url = URLDecoder.decode(url,"UTF-8");
        System.out.println(url);
        System.out.println("before get");
//        while ((lineTxt=bufferedReader.readLine())!=null){
        if (url.contains("#/log") || (url.contains("https://hk4e-api"))) {
            System.out.println(url);
            Integer uid = writeInService.writeIn(url);
            System.out.println(uid);
            if (uid > 0) {
                connectGachaUserService.insertUid(uid);
                return new Result<Integer>().ok(uid);
            } else {
                return new Result<Integer>().error(new InsertError().getInsertError(uid));
            }
//            }
        }
        return new Result<Integer>().error("文件有误");
    }

}
