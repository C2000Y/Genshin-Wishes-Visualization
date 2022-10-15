package com.cygs.gsweb.fileReader.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cygs.gsweb.fileReader.entity.FileReaderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author 赛勒一点也不努力
 */
@Repository
@Mapper
public interface FileReaderDao extends BaseMapper<FileReaderEntity> {
    String readImgFile();
    void writeImgFile(String data);
    List<FileReaderEntity> getDictionary();
    List<String> getAllItem();
    Integer updateDictionaryById(HashMap map);
    Integer addDictionary(HashMap map);
    Integer deleteDictionaryByID(Integer id);
}
