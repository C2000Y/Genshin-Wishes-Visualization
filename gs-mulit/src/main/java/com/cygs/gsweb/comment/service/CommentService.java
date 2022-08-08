package com.cygs.gsweb.comment.service;

import com.cygs.gsweb.comment.entity.CommentEntity;

import java.util.HashMap;
import java.util.List;

public interface CommentService {
    List<CommentEntity> getCommentsByUid(Integer uid);
    Integer sendComment(HashMap map);
}
