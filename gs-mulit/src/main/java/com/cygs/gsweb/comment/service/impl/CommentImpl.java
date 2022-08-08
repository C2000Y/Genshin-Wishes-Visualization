package com.cygs.gsweb.comment.service.impl;

import com.cygs.gsweb.comment.dao.CommentDao;
import com.cygs.gsweb.comment.entity.CommentEntity;
import com.cygs.gsweb.comment.service.CommentService;
import com.cygs.gsweb.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 赛勒一点也不努力
 */
@Service
public class CommentImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public List<CommentEntity> getCommentsByUid(Integer uid) {
        return commentDao.getCommentsByUid(uid);
    }

    @Override
    public Integer sendComment(HashMap map) {
        if(map.get("id").toString().compareTo(map.get("pid").toString()) == 0){
            String id = UUIDGenerator.getUUID(16);
            map.replace("id", id);
            map.replace("pid", id);
        }
        return commentDao.sendComment(map);
    }
}
