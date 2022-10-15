package com.cygs.gsweb.utils.file;

import com.baomidou.mybatisplus.extension.api.R;
import com.cygs.gsweb.utils.result.Result;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Reader {
    public Reader() {

    }

    static String imageJsonFileLocation = "ImageUrl.json";

    public static String readImageUrlData() throws IOException {
        String json = "null";
        json = readJsonData(imageJsonFileLocation);
        return json;
    }

    public static String readJsonData(String pactFile) throws IOException {
        StringBuilder strBuffer = new StringBuilder();
        ClassPathResource resource = new ClassPathResource(pactFile);
        InputStream fis = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(inputStreamReader);

        String str;
        while ((str = in.readLine()) != null) {
            strBuffer.append(str);  //new String(str,"UTF-8")
        }
        in.close();
        return strBuffer.toString();
    }

    public static void writeImageUrlData(String data) throws IOException {
        writeJsonData(imageJsonFileLocation, data);
    }

    public static void writeJsonData(String pactFile, String data) throws IOException {
        File myFile = new File(pactFile);
        BufferedWriter out = new BufferedWriter(new FileWriter(myFile));
        out.write(data);
        out.flush();
        out.close();
    }

    public static String readJsonDataJar() throws IOException {
        String json = "null";
        File file = new File("settings/ImageUrl.json");
        if (!file.exists()) {
            return null;
        }
        InputStream inputStream = new FileInputStream(file);
        return json;
    }

}
