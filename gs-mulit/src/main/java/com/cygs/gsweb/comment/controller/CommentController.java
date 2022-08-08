package com.cygs.gsweb.comment.controller;

import com.cygs.gsweb.comment.entity.CommentEntity;
import com.cygs.gsweb.comment.service.CommentService;
import com.cygs.gsweb.utils.result.InsertError;
import com.cygs.gsweb.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cyrus
 */
@RestController
@RequestMapping("comment")
public class CommentController implements Serializable {

    @Autowired
    CommentService commentService;

    /**
     * localhost:6480/comment/getCommentList?uid=100105164
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("getCommentList")
    public Result<List<CommentEntity>> getCommentList(@RequestParam("uid") Integer uid)throws Exception {
        List<CommentEntity> CommentEntity = commentService.getCommentsByUid(uid);
        return new Result<List<CommentEntity>>().ok(CommentEntity);
    }

    @CrossOrigin
    @GetMapping("sendComment")
    //    @ResponseBody
    public Result<Integer> sendComment(@RequestParam HashMap map) throws Exception {
        Integer uid = commentService.sendComment(map);
        return new Result<Integer>().ok(uid);
    }

}
