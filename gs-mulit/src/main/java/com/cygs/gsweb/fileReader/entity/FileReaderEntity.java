package com.cygs.gsweb.fileReader.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色up池
 *
 * @author 赛勒一点也不努力
 * @since 0.0.1 2021-12-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@TableName("dict")
public class FileReaderEntity {

    /**
     *  id
     */
    @TableId
    private Integer id;
    /**
     *  角色名称
     */
    private String name;

    /**
     *  url
     */
    private String url;

    /**
     * rank
     */
    private Integer rank;

    /**
     * 单位类型 1:角色，2:装备
     */
    private String type;

    /**
     * 角色名称_EN
     */
    private String nameEn;
}
