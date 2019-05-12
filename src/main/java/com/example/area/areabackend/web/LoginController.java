package com.example.area.areabackend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    private Object login(String code) {
        if (code != null && !"".equals(code)) {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxd7ee509bb50d9560&secret=9336a5e4135926375c0ce957875c8be3&js_code={code}&grant_type=authorization_code";
            Map<String, Object> uriVariables = new HashMap<String, Object>();
            uriVariables.put("code", code);
            try {
                return restTemplate.getForObject(url, String.class, uriVariables);
            } catch (Exception e) {
                logger.warn("获取用户信息失败: {}", e.toString());
                throw new RuntimeException("获取用户信息失败:" + e.toString());
            }
        } else {
            logger.warn("code不能为空");
            throw new RuntimeException("code不能为空");
        }
    }
}
