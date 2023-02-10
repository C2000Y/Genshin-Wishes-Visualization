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

// 用于测试
public class HTMLPageParser {
    public static void main(String[] args) throws Exception {
        //目的网页URL地址
//        String url = "
//        System.out.println("输入网址：");
//        OnGetWebViewPageFinish:https://webstatic.mihoyo.com/hk4e/event/e20190909gacha/index.html?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=c9ab84a4c606b3814afc40cef6adae793fb58c&timestamp=1637712297&lang=zh-cn&device_type=pc&ext=%7b%22loc%22%3a%7b%22x%22%3a-271.3077087402344%2c%22y%22%3a212.46453857421876%2c%22z%22%3a-973.8661499023438%7d%2c%22platform%22%3a%22WinST%22%7d&game_version=CNRELWin2.3.0_R5102609_S5082883_D5109732&plat_type=pc&region=cn_gf01&authkey=0YlDKvsqjT5W92wMgxHwIH7WsgwBqthgcuxq3WbhFw16XSDCDor0uwMZzfukhx8o9XDS%2bIOqpfPmXgiEkh3wcn6RnqMkEyHTyLGK7rOdnyuGt%2bbNnd9jv%2fYMeD2eG%2fBjtFVHZEBLrKb2uva%2fQzNVzRe5Co5AKbGYHsP65x9CkyjgMQG70rP%2fkDfXbErwdPloTTzVVA%2f9h7YoZE%2bcfp8d4gOGlzFtB7FhBCTDqVwuJQXmiwNMv%2bT%2fWNxD7MC0SAk0a6tGXaFmS%2f4KiI1FsUZG7sviTaApBbAl0MTqTlTVrJf9qi6%2f5RnG1DbZQecUv6rvs%2blG6nTv8kDyOXFFgjfm8sqtv9QxpFAGZwKz8zPaP2QCSA23a42oRh%2bgnQiCnCQSF8wVlv%2foQkhgsvAjotjuGDWC4q1Q46T6T1x6qo%2bGsaz2uiTNt7MKerPQ65bbQeaSNvkoRVKBvI0Y5SyS5E%2fXcHXiqbz5aGZy6JsLVpxIr5w9fifOXKMpuquZEcBn%2fCn3Ex59AY81TKWQKoeT8Nti3KPRTQmMPXFKA2E77f9QQcg6adqgP7gpXFEBVxE%2bTTAR7y8WdynFX70g7JA1rndK9z%2fH%2bLhvbT6pere9%2fTgasJcVzVg8gKRYRo2JkX9R00a5fx4Js1LBhL4lArkoA4n9KZKlUtJZNI%2bjb9BkrMNOhNVhIgRePUnqwssgsNn4A8q%2bRFxN90be%2bEH%2bQ9ArseJ1OR4VTvtfxsdV%2bpJfYBkXnw8lfCP16fn1TXlnSh%2fys34KpNI%2bqwfxEfA9%2fVrTBXHHdDMnAQbvHNPlVW3r05qeeHNDwc%2fHza8N9j%2bD1gpN9OKOKKNGZvLiHOjE1S1pFLwX5siC99SJ1dTjHdGp7k5c01LmEgWPnnTC1BCt4rvSqLrcImb57ZhRHHrWOi4KakM%2fbkPsXc1CCwXL%2bNCdTlj1%2fW3iGwW%2bqd741k97mVX3yrDWR0ewoosedCv%2bIrlM%2fXKUbC%2f3ggm%2f3m80UYPei1BbV%2brZokaoC1BbFn5r8Z4wN5gD&game_biz=hk4e_cn#/log
//        Scanner scan=new Scanner(System.in);
//        String url=scan.next();
//        String url="https://hk4e-api-os.hoyoverse.com/event/gacha_info/api/getGachaLog?win_mode=fullscreen&authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=7fd518e0876027ab39b8cc0cb9eb4a34dc477489&timestamp=1661298443&lang=en&device_type=pc&game_version=OSRELWin3.0.0_R9814673_S9869211_D10023725&plat_type=pc&region=os_usa&authkey=w9xgsFbyPW%2bsqfXr96QihGC559bQeETr%2bL%2bgRf%2fDiPUgiW1GKQErkEtqmBWatO2LICPPsbTKaKGilbVRBebVdLSpOotdB1JRxNL8VlH%2bBwa0pPNvuNWr6v%2fzxKcfHLw7%2bO%2bJnBpPfTURCQWJNZ9%2fZqb7PE%2frycAb0ePCIMbkoGGpOzR%2f4bOhKtN820kaUQqP3cDwUtW7Wscblnhl%2fb0tR6lWjWCpDyA6QsrTfVjWO%2bLm7Afc9l6cJ16GeD%2bJH2H8DY9AbBU5A%2bP9XE4BPo%2fPukHnqIOgD6MCHDptIGfH5M1zg6RWaWGHEEiVKXxnNzHxLVVl%2bhfdO3yzjXVKIPrXaDU2ouQJMH32MB%2b7lFIdjONr590o4JKoJ6LAu4AVAODgGAgwOJDh5LtXZxrYl1xGuElmlwbtxLvXm27zvTfdEy%2f76UKY6zyEbOEn%2ficuoKGzDpJrtmCJAaCl8JK2JtBb7qVJa4j94spYhryNCJqYNdumyUznFU7msC9W5ihKKVLCzdgOVAYdlDEbcWj05ZEk5ckrgNpEZK6tsBYzoYad7pT8kyUqXAbAGQyv9TI%2bkPyG%2fJCJBSJhmgUgTAouHcZSTn49huJyQjjS1lobwDt1jG9JUdkWEMdsyJgzMF9mPdNmnKEYE2eZB4N3gNHKMYNCPHTcKf6OWw16LiDtO5ClRmlP65FdpuRXNgD6CQjdqL7xvFWI9fuUoOF2yLh8I6kgDdFGdkorBWEpirRD1vZfyWt6E2j1x1YuaaV98Yq6hMIr7AVmNMZHJoUxK2J8bNWxMzkPpkLJyk%2fLdkDgesgMNtYMTn8GUKgqhJ32U%2fZb8vHaX7Slwrt43dQYpzqM%2fCs7xF6JzME7szsF61T6b8IwnRFojGuJpr%2bPqAWIVj2f%2bb9zRZpQ%2bV13S9RGZGFRyrwHGPHTJPfnpr5vjFfe73MXdjhAAUhZ3N%2bAHPNceYjT2VZeKnezSYQwEsXhVWPfV3G4JN9erSPvSvMOq%2fXZfSbnThh%2bHcUCXc6NnKF3bip2zQlLBG0xIkQEc1OVQAtY707ytPz8vgel53v5NwqT4Qngngjwgh990cc4L9UNaGImyCqe02nOKhNfKdKulIzkl0BvxoZ8xVsTlchjovxTtstYibyO7eB4lum0ozuDzJUHjQ1hGc27QBAuDKTsEr6Pqc2PK1tPW0ItT8zl0utsmsQBZ1%2fL97l%2fGUi15wBRm36kCNwYV2FuSYtCDJA0f70cbvXdoNEysTtiZbkKzRyo580s8iMxHq5Irsco95GyZArTP6QIM697Fn5JK4qnRL%2fkcKns4mlwoEmZu4Qt0FQDmMe49HfmRn6AX00zObL48LUN4N0Ei2WTmNdcMOPQ1oLGaOT1wg%3d%3d&game_biz=hk4e_global&gacha_type=301";
        String url="https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?win_mode=fullscreen&authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=c0338b9172081a6391561fefbda0cdf8ee439421&timestamp=1664323764&lang=zh-cn&device_type=pc&game_version=CNRELWin3.1.0_R10916590_S10805493_D10941477&plat_type=pc&region=cn_gf01&authkey=5h4k7eeClGbOqbSwJjvMHdw%2b4Z6pzBUk29MSUumgSg4KgCObSfIX9G3uRjV5s8eBlAarK2q%2byfDGrRLydZ32Lx2erfl4yhdy2Az3BxzdoBrEgTSGZGOC4DbGW4rxiyUKTeqbuuxIf3SdtEgDeDTFCljX%2fXKXRj5kQoKQynzFITUiY13n7fN%2bx5425Fx5f0KxDrzGQwn14%2b25MJMtdmRbM12wuz5wF7%2bKrIpbCN%2b2vNmUkLhSaKtXzKuhxJEcbyadBB0X7R6N6ccwWjPn5zpSO2OiA0xubcG2wuSinFjgFEzPbyCH%2blShmJeFbuEz0OUqLJ6a%2fPwj2yn%2bR1s0gPRc36U%2bJXiyMG098ZRSSsWaZc19N1VAyhkQKFYBRrRZFvHgS9alaIsFzupqxNSL2sdI7FWcEX3Zmj25pQ7NraB387Gbq%2fKCKQN2u4m0icVY6c3YUEFdfzgZ06s1z7oWa9W6%2ffVtNImKd2FxUdeJMcBbRWRFeattiHo5mHPfkVbEaRTvKMumWx%2b0Euz7mrFpyadkV53feBh0DL%2fnwU0scU0zFV911jfCC7uIYpWIMcjs8JyfV7pv58xuTWjwytvyTXdIxTaUhfmpuasiCEpeFV4fcVfaJbUW68chaaA2FhrP11qK8VOMtW4FB6TNcUfAVQTchn5I3ujW0aHn1fTebj7yJK%2bLw2H5h0qMIkiHKDC9qZ0VfiF84wCLSf%2bn%2fu%2bchDOwpY1J%2f1RS4bgZoYq0X4RqlDuHmcEaASqKOnerf2BXlGwz86o%2f03X1mbNTn6fSopu6bLIbRvnrSidSKmUFor0Q8X8D72FeXiFrhaocbmIoPtMgHgnXpsUOSqi6z1IBxE0msKeUe8Dt3tsBLZWVJAHpW6lTjr3JHEO%2bOLHn6egu7AJFlp0lqJw2FG6I942fOpUTdvast6HZgOD4zbgJlM92ZzdcthrX3x2LSlnvhTS5a%2f%2fFed%2f5fdDFms2R8xf12uvlaox%2bSi8i1EglCw9bDxBbKOVeM8cIjf8u1m4Tz1K8iSFvcOCBLlqo6I%2fQikQDvQYmlwjMOr%2b%2fZ8tL%2frN6i8L0O8DhUjIhuHiHi%2bIEi5IpDKK%2bZDwooaPUNaSWFQzFdjrV7axIsf%2f56ArQNxn329Vo094%2f8%2fq4lHAIcxwjCZHZampjg%2fYgq3Nek574yBHDjmr1kEr5hzk1P55MJHD23crPJtTIWrY5FSmr7%2fo7yjnZARhapLd%2bGBI4XGYSQG1v%2fDluKHzy%2bRghKQPkgc8ce4h8%2bgBGwYi639%2fXwWGWsYKItHZxllapW%2fVcBTqES%2fgnkmaUCXRy4k3MeoeWoZsDRpidYriMcza1gB%2b%2fOeuUWscai027EisWMPbebzwzn%2bjfQmASuw%3d%3d&game_biz=hk4e_cn";
        System.out.println("开始读取...");
        getSummon(convertUrl(url,"200"));
        getSummon(convertUrl(url,"301"));
        getSummon(convertUrl(url,"302"));
    }
    public static List<Gacha> getSummon(String urlInfo) throws Exception {
        String endId = "0";
        int count = 0;
        boolean flag = true;
        List<Gacha> gachaList = new ArrayList<Gacha>();

        while (flag) {
            URL url = new URL(urlInfo + endId);
            System.out.println(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line != null) {
                DataJson data = JSON.parseObject(line, DataJson.class);
                if (data.getRetcode() < 0) {
                    throw new Exception("请求异常");
                }
                try {
                    int total = data.getData().getList().size();
                    flag = total >= Integer.parseInt(data.getData().getSize());
                    if(data.getData().getList().size() > 0 ) {
                        for (int i = 0; i < total; i++) {
                            ListJson listJson = data.getData().getList().get(i);
                            Gacha gacha = new Gacha(listJson.getId(), listJson.getUid(), listJson.getName(), listJson.getItem_type(), listJson.getRank_type(), listJson.getTime());
                            gachaList.add(gacha);
                        }
                        endId = data.getData().getList().get(total - 1).getId();
                    }
                    else {
                        break;
                    }
                    reader.close();
                } catch (Exception e) {
                    System.out.println(e);
                    throw new Exception("数据错误");
                }
            } else {
                break;
            }
            count++;
            System.out.println("进度：第" + count + "页，解析中...");
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                throw new Exception("d");
            }
        }
        System.out.println(gachaList.toString());
        return gachaList;
    }

    public static String convertUrl(String urlInfo, String poolType){
//        if (urlInfo.contains("webstatic.mihoyo.com/hk4e/event/e20190909gacha-v2/index.html")) {
//
//        }
        if (urlInfo.contains("game_biz=hk4e_cn")){
            urlInfo = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?" + urlInfo.substring(urlInfo.indexOf("?")+1);
        }
        else if (urlInfo.contains("game_biz=hk4e_global")) {
            urlInfo = "https://hk4e-api-os.hoyoverse.com/event/gacha_info/api/getGachaLog?" + urlInfo.substring(urlInfo.indexOf("?")+1);
        }
        urlInfo = urlInfo.replaceAll("#/log.*$","");
        urlInfo = urlInfo.replaceAll("lang=en","lang=zh-cn");
        urlInfo += "&gacha_type="+poolType+"&page=1&size=20&end_id=";
        return urlInfo;
    }



}
