package com.xiaolc.controller;

import com.xiaolc.model.ActIdUser;
import com.xiaolc.service.ActIdUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: lc
 * @Date: 2019/6/21 13:29
 */
@RestController
@RequestMapping("/ActIdUser")
@Api(tags = { "ActIdUser-controller" }, description = "弹框-账号")
public class ActIdUserController {

    @Autowired
    private ActIdUserService actIdUserService;

    @PostMapping("添加用户")
    public ActIdUser save(ActIdUser actIdUser){
        actIdUser.setId(UUID.randomUUID().toString());
        return actIdUserService.save(actIdUser);
    }

}
