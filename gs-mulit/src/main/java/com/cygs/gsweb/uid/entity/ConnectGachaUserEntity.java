package com.cygs.gsweb.uid.entity;

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
@TableName("connect")
public class ConnectGachaUserEntity{

    /**
     *  uid
     */
    private String uid;

    /**
     * 玩家id
     */
    private String userId;

}
