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
    public Result<List<CommentEntity>> getCommentList(@RequestParam HashMap map)throws Exception {
        map.put("pageSize", 10);
        map.put("page",new Integer(map.get("page").toString()));
//        System.out.println(map);
        List<CommentEntity> CommentEntity = commentService.getCommentsByUid(map);
        return new Result<List<CommentEntity>>().ok(CommentEntity);
    }

    @CrossOrigin
    @PostMapping("sendComment")
    //    @ResponseBody
    public Result<Integer> sendComment(@RequestBody HashMap map) throws Exception {
        System.out.println(map);
        Integer res = commentService.sendComment(map);
        return new Result<Integer>().ok(res);
    }

}
