package com.cygs.gsweb.comment.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@TableName("comments")
public class CommentEntity {
    /**
     * Id
     */
    @TableId
    private String id;

    /**
     * pid
     */
    private String pid;

    /**
     * 玩家id
     */
    private String uid;

    /**
     * 玩家id
     */
    private String comment;

    /**
     * 发评论的日期
     */
    private Date postDate;

    /**
     * 评分
     */
    private Integer rate;

    /**
     * 是否置顶
     */
    private Integer isTop;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }
}
