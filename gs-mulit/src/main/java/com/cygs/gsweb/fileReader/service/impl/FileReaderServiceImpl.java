package com.cygs.gsweb.fileReader.service.impl;

import com.cygs.gsweb.fileReader.dao.FileReaderDao;
import com.cygs.gsweb.fileReader.entity.FileReaderEntity;
import com.cygs.gsweb.fileReader.service.FileReaderService;
import com.cygs.gsweb.utils.file.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class FileReaderServiceImpl implements FileReaderService {

    @Autowired
    FileReaderDao fileReaderDao;

    @Override
    public String readImgFile() throws IOException {
        return Reader.readImageUrlData();
    }

    @Override
    public void writeImgFile(String data) throws IOException {
        Reader.writeImageUrlData(data);
    }

    @Override
    public List<FileReaderEntity> getDictionary() {
        return fileReaderDao.getDictionary();
    }

    @Override
    public List<String> getAllItem() {
        return fileReaderDao.getAllItem();
    }

    @Override
    public Integer updateDictionaryById(HashMap map) {
        return fileReaderDao.updateDictionaryById(map);
    }

    @Override
    public Integer addDictionary(HashMap map) {
        return fileReaderDao.addDictionary(map);
    }

    @Override
    public Integer deleteDictionaryByID(Integer id) {
        return fileReaderDao.deleteDictionaryByID(id);
    }

}
