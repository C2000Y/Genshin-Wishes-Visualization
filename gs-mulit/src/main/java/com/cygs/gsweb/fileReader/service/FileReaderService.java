package com.cygs.gsweb.fileReader.service;

import com.cygs.gsweb.fileReader.entity.FileReaderEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface FileReaderService {
    String readImgFile() throws IOException;
    void writeImgFile(String data) throws IOException;
    List<FileReaderEntity> getDictionary();
    List<String> getAllItem();
    Integer updateDictionaryById(HashMap map);
    Integer addDictionary(HashMap map);
    Integer deleteDictionaryByID(Integer id);
}
