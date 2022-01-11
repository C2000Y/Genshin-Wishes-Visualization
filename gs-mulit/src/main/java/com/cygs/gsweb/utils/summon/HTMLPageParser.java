package com.cygs.gsweb.utils.summon;

import java.util.Scanner;

public class HTMLPageParser {
//    public static void main(String[] args) throws Exception {
//        //目的网页URL地址
////        String url = "
//        System.out.println("输入网址：");
////        OnGetWebViewPageFinish:https://webstatic.mihoyo.com/hk4e/event/e20190909gacha/index.html?authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=c9ab84a4c606b3814afc40cef6adae793fb58c&timestamp=1637712297&lang=zh-cn&device_type=pc&ext=%7b%22loc%22%3a%7b%22x%22%3a-271.3077087402344%2c%22y%22%3a212.46453857421876%2c%22z%22%3a-973.8661499023438%7d%2c%22platform%22%3a%22WinST%22%7d&game_version=CNRELWin2.3.0_R5102609_S5082883_D5109732&plat_type=pc&region=cn_gf01&authkey=0YlDKvsqjT5W92wMgxHwIH7WsgwBqthgcuxq3WbhFw16XSDCDor0uwMZzfukhx8o9XDS%2bIOqpfPmXgiEkh3wcn6RnqMkEyHTyLGK7rOdnyuGt%2bbNnd9jv%2fYMeD2eG%2fBjtFVHZEBLrKb2uva%2fQzNVzRe5Co5AKbGYHsP65x9CkyjgMQG70rP%2fkDfXbErwdPloTTzVVA%2f9h7YoZE%2bcfp8d4gOGlzFtB7FhBCTDqVwuJQXmiwNMv%2bT%2fWNxD7MC0SAk0a6tGXaFmS%2f4KiI1FsUZG7sviTaApBbAl0MTqTlTVrJf9qi6%2f5RnG1DbZQecUv6rvs%2blG6nTv8kDyOXFFgjfm8sqtv9QxpFAGZwKz8zPaP2QCSA23a42oRh%2bgnQiCnCQSF8wVlv%2foQkhgsvAjotjuGDWC4q1Q46T6T1x6qo%2bGsaz2uiTNt7MKerPQ65bbQeaSNvkoRVKBvI0Y5SyS5E%2fXcHXiqbz5aGZy6JsLVpxIr5w9fifOXKMpuquZEcBn%2fCn3Ex59AY81TKWQKoeT8Nti3KPRTQmMPXFKA2E77f9QQcg6adqgP7gpXFEBVxE%2bTTAR7y8WdynFX70g7JA1rndK9z%2fH%2bLhvbT6pere9%2fTgasJcVzVg8gKRYRo2JkX9R00a5fx4Js1LBhL4lArkoA4n9KZKlUtJZNI%2bjb9BkrMNOhNVhIgRePUnqwssgsNn4A8q%2bRFxN90be%2bEH%2bQ9ArseJ1OR4VTvtfxsdV%2bpJfYBkXnw8lfCP16fn1TXlnSh%2fys34KpNI%2bqwfxEfA9%2fVrTBXHHdDMnAQbvHNPlVW3r05qeeHNDwc%2fHza8N9j%2bD1gpN9OKOKKNGZvLiHOjE1S1pFLwX5siC99SJ1dTjHdGp7k5c01LmEgWPnnTC1BCt4rvSqLrcImb57ZhRHHrWOi4KakM%2fbkPsXc1CCwXL%2bNCdTlj1%2fW3iGwW%2bqd741k97mVX3yrDWR0ewoosedCv%2bIrlM%2fXKUbC%2f3ggm%2f3m80UYPei1BbV%2brZokaoC1BbFn5r8Z4wN5gD&game_biz=hk4e_cn#/log
//        Scanner scan=new Scanner(System.in);
//        String url=scan.next();
//        System.out.println("开始读取...");
//        String poolType = "200";
//        getURLInfo(convertUrl(url,poolType),poolType);
//        poolType = "301";
//        getURLInfo(convertUrl(url,poolType),poolType);
//        poolType = "302";
//        getURLInfo(convertUrl(url,poolType),poolType);

//    }
//
//    public static void getURLInfo(String urlInfo, String poolType) throws Exception {
//        System.out.println(urlInfo);
//        CharacterSummon characterSummon = new CharacterSummon();
////        characterSummon.getCharacters(urlInfo,endId);
//        characterSummon.InsertCharacters(urlInfo,poolType);
//
//    }

    public static String convertUrl(String urlInfo, String poolType){
        urlInfo = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?" + urlInfo.substring(urlInfo.indexOf("?")+1);
        urlInfo = urlInfo.replaceAll("#/log","");
        urlInfo += "&gacha_type="+poolType+"&page=1&size=20&end_id=";
        System.out.println(urlInfo);
        return urlInfo;
    }
}