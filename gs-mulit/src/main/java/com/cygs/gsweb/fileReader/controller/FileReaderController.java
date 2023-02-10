package com.cygs.gsweb.fileReader.controller;

import com.cygs.gsweb.character301.service.WriteInService;
import com.cygs.gsweb.connectGachaUser.service.ConnectGachaUserService;
import com.cygs.gsweb.fileReader.entity.FileReaderEntity;
import com.cygs.gsweb.fileReader.service.FileReaderService;
import com.cygs.gsweb.utils.result.InsertError;
import com.cygs.gsweb.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cyrus
 */
@RestController
@RequestMapping("file")
public class FileReaderController implements Serializable {

    @Autowired
    WriteInService writeInService;
    @Autowired
    ConnectGachaUserService connectGachaUserService;
    @Autowired
    FileReaderService fileReaderService;

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
        if (url.contains("#/log") || url.contains("https://hk4e-api") || url.contains("https://webstatic.mihoyo.com") || url.contains("game_biz=hk4e_cn") || url.contains("game_biz=hk4e_global")) {
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

    @CrossOrigin
    @GetMapping("/getImgJson")
    public Result<String> getImgJson () throws IOException {
        try{
            String res = fileReaderService.readImgFile();
            return new Result<String>().ok(res);
        } catch (Exception e){
            return new Result<String>().error("读取错误");
        }
    }
//    获取所有的角色和物品信息
    @CrossOrigin
    @GetMapping("/getDictionary")
    public Result<Map<Object, Object>> getDictionary (){
        List<FileReaderEntity> fileReaderEntities = fileReaderService.getDictionary();
        Map<Object, Object> map = new HashMap<>();
        for (FileReaderEntity fileReader: fileReaderEntities) {
            map.put(fileReader.getName(), fileReader);
        }
        return new Result<Map<Object, Object>>().ok(map);
    }

//    根据id更新角色或物品的信息
    @CrossOrigin
    @PutMapping("/updateDictionary")
    public Result<Integer> updateDictionary (@RequestBody HashMap map){
        System.out.println(map);
        Integer fileReaderEntities = fileReaderService.updateDictionaryById(map);
        if (fileReaderEntities == 1){
            return new Result<Integer>().ok(fileReaderEntities);
        }
        else{
            return new Result<Integer>().error(fileReaderEntities);
        }
    }

//    根据id更新角色或物品的信息
    @CrossOrigin
    @PostMapping("/addDictionary")
    public Result<Integer> addDictionary (@RequestBody HashMap map){
        Integer fileReaderEntities = fileReaderService.addDictionary(map);
        if (fileReaderEntities == 1){
            return new Result<Integer>().ok(fileReaderEntities);
        }
        else{
            return new Result<Integer>().error(fileReaderEntities);
        }
    }

    //    根据id更新角色或物品的信息
    @CrossOrigin
    @PutMapping("/deleteDictionary")
    public Result<Integer> deleteDictionary (@RequestBody HashMap map){
        System.out.println(map);
        Integer fileReaderEntities = fileReaderService.deleteDictionaryByID((Integer) map.get("id"));
        if (fileReaderEntities == 1){
            return new Result<Integer>().ok(fileReaderEntities);
        }
        else{
            return new Result<Integer>().error(fileReaderEntities);
        }
    }

//    获取所有的角色
    @CrossOrigin
    @GetMapping("/getAllItem")
    public Result<List<String>> getAllItem (){
        List<String> stringList = fileReaderService.getAllItem();
        return new Result<List<String>>().ok(stringList);
    }

    @CrossOrigin
    @PostMapping("/updateImgJson")
    public Result<String> updateImgJson (@RequestBody String data) throws IOException {
        data = URLDecoder.decode(data,"UTF-8");
        data = data.replaceAll("},","},\n");
        data = data.replaceAll("}=","}");
        try{
            fileReaderService.writeImgFile(data);
            return new Result<String>().ok("写入成功");
        } catch (Exception e){
            return new Result<String>().error("读取错误");
        }
    }

    @GetMapping("/testFile")
    public Result<String> testFile() throws IOException {
        StringBuilder strBuffer = new StringBuilder();
        String path = "";
        Path wipPath = Paths.get("/ImageUrl.json");
        try (BufferedReader reader = Files.newBufferedReader(wipPath)) {
            String line=null;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        return new Result<String>().ok(path);
    }

}
