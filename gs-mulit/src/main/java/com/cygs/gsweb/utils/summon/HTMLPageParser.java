package com.cygs.gsweb.utils.summon;

import com.alibaba.fastjson.JSON;
import com.cygs.gsweb.character301.dto.DataJson;
import com.cygs.gsweb.character301.dto.Gacha;
import com.cygs.gsweb.character301.dto.ListJson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HTMLPageParser {
    public static void main(String[] args) throws Exception {
        //目的网页URL地址
//        String url = "
//        System.out.println("输入网址：");
//        OnGetWebViewPageFinish:https://webstatic.mihoyo.com/hk4e/event/e20190909gacha/index.html?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=c9ab84a4c606b3814afc40cef6adae793fb58c&timestamp=1637712297&lang=zh-cn&device_type=pc&ext=%7b%22loc%22%3a%7b%22x%22%3a-271.3077087402344%2c%22y%22%3a212.46453857421876%2c%22z%22%3a-973.8661499023438%7d%2c%22platform%22%3a%22WinST%22%7d&game_version=CNRELWin2.3.0_R5102609_S5082883_D5109732&plat_type=pc&region=cn_gf01&authkey=0YlDKvsqjT5W92wMgxHwIH7WsgwBqthgcuxq3WbhFw16XSDCDor0uwMZzfukhx8o9XDS%2bIOqpfPmXgiEkh3wcn6RnqMkEyHTyLGK7rOdnyuGt%2bbNnd9jv%2fYMeD2eG%2fBjtFVHZEBLrKb2uva%2fQzNVzRe5Co5AKbGYHsP65x9CkyjgMQG70rP%2fkDfXbErwdPloTTzVVA%2f9h7YoZE%2bcfp8d4gOGlzFtB7FhBCTDqVwuJQXmiwNMv%2bT%2fWNxD7MC0SAk0a6tGXaFmS%2f4KiI1FsUZG7sviTaApBbAl0MTqTlTVrJf9qi6%2f5RnG1DbZQecUv6rvs%2blG6nTv8kDyOXFFgjfm8sqtv9QxpFAGZwKz8zPaP2QCSA23a42oRh%2bgnQiCnCQSF8wVlv%2foQkhgsvAjotjuGDWC4q1Q46T6T1x6qo%2bGsaz2uiTNt7MKerPQ65bbQeaSNvkoRVKBvI0Y5SyS5E%2fXcHXiqbz5aGZy6JsLVpxIr5w9fifOXKMpuquZEcBn%2fCn3Ex59AY81TKWQKoeT8Nti3KPRTQmMPXFKA2E77f9QQcg6adqgP7gpXFEBVxE%2bTTAR7y8WdynFX70g7JA1rndK9z%2fH%2bLhvbT6pere9%2fTgasJcVzVg8gKRYRo2JkX9R00a5fx4Js1LBhL4lArkoA4n9KZKlUtJZNI%2bjb9BkrMNOhNVhIgRePUnqwssgsNn4A8q%2bRFxN90be%2bEH%2bQ9ArseJ1OR4VTvtfxsdV%2bpJfYBkXnw8lfCP16fn1TXlnSh%2fys34KpNI%2bqwfxEfA9%2fVrTBXHHdDMnAQbvHNPlVW3r05qeeHNDwc%2fHza8N9j%2bD1gpN9OKOKKNGZvLiHOjE1S1pFLwX5siC99SJ1dTjHdGp7k5c01LmEgWPnnTC1BCt4rvSqLrcImb57ZhRHHrWOi4KakM%2fbkPsXc1CCwXL%2bNCdTlj1%2fW3iGwW%2bqd741k97mVX3yrDWR0ewoosedCv%2bIrlM%2fXKUbC%2f3ggm%2f3m80UYPei1BbV%2brZokaoC1BbFn5r8Z4wN5gD&game_biz=hk4e_cn#/log
//        Scanner scan=new Scanner(System.in);
//        String url=scan.next();
        String url="位于 https://webstatic.mihoyo.com/hk4e/event/e20190909gacha/index.html?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=e432d80a79eb2a8953331fe921c7b6dcf25aa808&timestamp=1657669515&lang=zh-cn&device_type=mobile&ext=%7b%22loc%22%3a%7b%22x%22%3a-276.8004455566406%2c%22y%22%3a121.88774871826172%2c%22z%22%3a424.3658752441406%7d%2c%22platform%22%3a%22Android%22%7d&game_version=CNRELAndroid2.8.0_R8891447_S8883030_D8644052&plat_type=android&region=cn_gf01&authkey=j3mSAk2MB3jplEHEuNWlJ2AKBVesxQ9VLOJCc4alT4vZI1ua%2bqc4yDhP42YD8vwctMfQqDyx2l4nkIK9LUxCrO0ARWAA%2fOw8kpC3CjywtRFIlR%2fdl%2bqy2gSL65sCKaoCafvpwiQWZ6z%2bJLnubJ0s4p6v6rBGAyb1t4R683FaYWYAZCoaYEFPZBmGDmw4hWUYYpX8%2b3de3P9jcfStQWhp7PRr88sqRsGnPvATPlzDBqRXKi0sZBO%2fSBqxj8PEdqRJH%2b4t%2bpnn8PfSipN5I2mQh1ePLxYkn4IrLTLM%2b1ANysBjT7RVMK%2f%2bYPwTqBVhrd9alPMD8QLg7MyeggPZPNbUvRy0xV4k5xaCCcdTJylWqAYstaJenuIo9%2bpFJD%2fwCs0wZc2EUGJlSkqOvYpfNxm6xcJdRzjUK2DazmzacLmPOuP4Wpu6rd8hPFcMVC8MAbpLVswBcwOHS4yz3B72y9ohPIXLJjGavLQw8fNDe4CIXzDaaZZwa9rqMVIJJ2n5qs2pq6a4uL1srNz%2fmdqKGIiLuTZPYcH%2fM9SQQS6J6P20c4Y48HuGCSc3y8Ex92CTtr3Fk3nLEth%2ffT%2fF5VKFPcKvjBWZwgNBc7tpMkZFUHwnLArAxXUyEiQXeWze%2fGTbBRzLJfXxATNtotkxZjxJ1Vf3wmAQujoWO2kaIxmVH8mJmppFThNla3DYLf69A6JP5Tc5FXNL1FcyFrwPp86dbeXEYg%2bcR6bOZ8JLS6T34yNYKtRPEna3b93lE4dy%2bs2dn3UdDyj6LyGMD%2bppJBFtE%2bznSOqLwhD83XE05Mk3KxFDtfW4%2fOpP9wzvF1z0T5oOjRr%2fCvMQNQdytZ9fM3EgQ2ccnk7%2b5C3osw4FZ7Ruj5QqZaWL%2fxHsrFAe%2f5QHZe501STBVzndmk3iIg7qHJfX7wKURUHiqlwHAFeWv%2fhGpIPYybxARliFjxVbV8lslM6kSHhPJuYaX7xVkQjB%2b%2fxMoUoRH1iHdayBEGwjGzUMgLoj8mxK9iwCrg7oIWoFPa4RYTtGF2uFDFmqgWL5e0G0lPuJj8eSK1CYku58CSNmJuionXfZR7SYLCwXEX5OcQphf2DHdzCNq6hen2%2fduqfTd1BXj5btDADrSePSMxhrv6skUY1YiPGMrzoXXT7Bvo5sgdnwHWD5zRjZ4nslGN66k5czCuUkVfjhsEzhjXqHrLbLdYUoWNPAE%2fCx3UwwHR2dKSafivzltV%2bomE1vxut2piN4fEJ%2bcUE80NebWMdQFeW8cDX4zbkzE9gWwFylU6HiXRjI6TlFk17sUmBOB%2fnW8ezqd3FqsA9ojBvP8U2OzxHzlbSpAs7JNT5eY5fmEQlgowoUcO19ikkr8L2pmrm%2b0XX8KQ%3d%3d&game_biz=hk4e_cn#/log 的网页无法加载，因为：";
        System.out.println("开始读取...");
        String poolType = "200";
        getURLInfo(convertUrl(url,poolType),poolType);
        poolType = "301";
        getURLInfo(convertUrl(url,poolType),poolType);
        poolType = "302";
        getURLInfo(convertUrl(url,poolType),poolType);
//        System.out.println(convertUrl(url,poolType));

    }

    public static void getURLInfo(String urlInfo, String poolType) throws Exception {
        System.out.println(urlInfo);
//        CharacterSummon characterSummon = new CharacterSummon();
//        characterSummon.getCharacters(urlInfo,endId);
        insertSummon(urlInfo,poolType);

    }
    static Integer insertSummon(String urlInfo, String type) throws Exception {
        String endId = "0";
        int count = 0;
        Boolean flag = true;
        List<Gacha> gachaList = new ArrayList<Gacha>();

        while (flag) {
            URL url = new URL(urlInfo + endId);
            System.out.println(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            if ((line = reader.readLine()) != null) {
                DataJson data = JSON.parseObject(line, DataJson.class);
                if (data.getRetcode() < 0) {
                    return -1;
                }
                try {
                    Integer total = data.getData().getList().size();
                    flag = total >= Integer.valueOf(data.getData().getSize());
                    for (int i = 0; i < total; i++) {
                        ListJson listJson = data.getData().getList().get(i);
                        Gacha gacha = new Gacha(listJson.getId(), listJson.getUid(), listJson.getName(), listJson.getItem_type(), listJson.getRank_type(), listJson.getTime());
                        gachaList.add(gacha);
                    }
                    endId = data.getData().getList().get(total - 1).getId();
                    reader.close();
                } catch (Exception e) {
                    System.out.println(e);
                    break;
                }
            } else {
                break;
            }
            count++;
            System.out.println("进度：第" + count + "页，解析中...");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
        System.out.println(gachaList.toString());
        return 0;
    }

    public static String convertUrl(String urlInfo, String poolType){
        urlInfo = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?" + urlInfo.substring(urlInfo.indexOf("?")+1);
        urlInfo = urlInfo.replaceAll("#/log.*$","");
        urlInfo += "&gacha_type="+poolType+"&page=1&size=20&end_id=";
        return urlInfo;
    }

}