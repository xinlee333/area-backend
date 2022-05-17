package com.example.area.areabackend.service.impl.web;

import com.example.area.areabackend.common.Ret;
import com.example.area.areabackend.entity.*;
import com.example.area.areabackend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {
    @Autowired
    private IndexService indexService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    // 注册新用户
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @CrossOrigin
    private Ret signUp(@RequestBody SignUp signUp) {
        return indexService.signUp(signUp.getUsername(), signUp.getPassword());
    }

    // 老用户登陆
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @CrossOrigin
    private Ret signIn(@RequestBody SignIn signIn) {
        return indexService.signIn(signIn.getUsername(), signIn.getPassword(), signIn.getGameTag());
    }

    // 免密登陆
    @RequestMapping(value = "/signinex", method = RequestMethod.POST)
    @CrossOrigin
    private Map<String, Object> signInEx(@RequestBody SignInEx signInEx) {
        // 验证

        // 通过
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("token", signInEx.getToken());
        return ret;
    }

    // 保存用户成绩
    @RequestMapping(value = "/savescore", method = RequestMethod.POST)
    @CrossOrigin
    private Ret saveScore(@RequestBody SaveScore saveScore) {
        return indexService.saveScore(saveScore.getToken(), saveScore.getGameTag(), saveScore.getLevel(), saveScore.getScore());
    }
}
