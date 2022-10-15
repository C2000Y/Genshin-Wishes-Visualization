package com.cygs.gsweb.user.controller;

import com.cygs.gsweb.user.entity.UserEntity;
import com.cygs.gsweb.utils.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("user")
public class LoginController {
    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public Result<String> login(@RequestBody UserEntity requestUser, HttpSession session) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
//        UserEntity user = userService.get(username, requestUser.getPassword());

        if (!Objects.equals("admin", username) || !Objects.equals("json", requestUser.getPassword())) {
            String message = "账号密码错误";
            return new Result<String>().error(message);
        } else {
            session.setAttribute("user", requestUser);
//            session.setAttribute("user", requestUser);
            return new Result<String>().ok("成功");
        }
    }
}
