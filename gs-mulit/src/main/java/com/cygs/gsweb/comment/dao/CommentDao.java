package com.cygs.gsweb.comment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cygs.gsweb.comment.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 赛勒一点也不努力
 */
@Repository
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {
    List<CommentEntity> getCommentsByUid(Integer uid);
    Integer sendComment(HashMap map);
}
