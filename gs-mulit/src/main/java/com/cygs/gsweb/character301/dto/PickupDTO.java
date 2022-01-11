package com.cygs.gsweb.character301.dto;

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
//@TableName("gacha_cn_301")
public class PickupDTO {

    /**
     *  抽卡id
     */
    private String id;

    /**
     * 玩家id
     */
    private String uid;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 单位类型
     */
    private String itemType;

    /**
     * 单位星级
     */
    private String rankType;

    /**
     * 抽卡时间
     */
    private Date time;

    /**
     * 抽取次数
     */
    private Integer count;

}
