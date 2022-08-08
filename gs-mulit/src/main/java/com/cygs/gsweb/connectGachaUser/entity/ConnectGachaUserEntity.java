package com.cygs.gsweb.connectGachaUser.entity;

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
@TableName("connect_gacha_user")
public class ConnectGachaUserEntity {
    /**
     *  uid
     */
    @TableId
    private Integer uid;

    /**
     * 玩家id
     */
//    private String userId;

    /**
     * 名称
     */
    private String name;


    /**
     * 上次更新的日期
     */
    private Date lastUpdate;

}
