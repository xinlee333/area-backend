package com.example.area.areabackend.web;

import com.example.area.areabackend.entity.Topic;
import com.example.area.areabackend.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

    /**
     * 列出所有的话题,并根据时间顺序(id记录插入顺序)排序
     *
     * @return topicList
     */
    @RequestMapping(value = "/gettopics", method = RequestMethod.GET)
    private ArrayList<Object> getTopics() {
        ArrayList<Object> topicsList = new ArrayList<Object>();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Topic> topics = new ArrayList<Topic>();
        //获取话题列表
        topics = topicService.getTopics();
        ArrayList<HashMap> itemList = new ArrayList<HashMap>();
        for (Topic topic : topics) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("id", topic.getId());
            item.put("openId", topic.getOpenId());
            item.put("date", topic.getDate());
            item.put("title", topic.getTitle());
            item.put("content", topic.getContent());
            item.put("connect", topic.getConnect());
            item.put("tag", topic.getTag());
            item.put("nickName", topic.getNickName());
            item.put("avatarUrl", topic.getAvatarUrl());
            item.put("gender", topic.getGender());
            item.put("province", topic.getProvince());
            item.put("city", topic.getCity());
            item.put("country", topic.getCountry());
            itemList.add(item);
        }

        modelMap.put("item", itemList);
        modelMap.put("type", "topic");
        topicsList.add(modelMap);

        logger.info("gettopics result: {}", topicsList);
        return topicsList;
    }

    /**
     * 根据id获取对应的话题
     * @param id
     * @return topic
     */
    @RequestMapping(value = "/querytopic", method = RequestMethod.GET)
    private Map<String, Object> queryTopic(Integer id) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Topic topic = topicService.getTopicById(id);
        modelMap.put("openId", topic.getOpenId());
        modelMap.put("date", topic.getDate());
        modelMap.put("title", topic.getTitle());
        modelMap.put("content", topic.getContent());
        modelMap.put("connect", topic.getConnect());
        modelMap.put("tag", topic.getTag());
        modelMap.put("nickName", topic.getNickName());
        modelMap.put("avatarUrl", topic.getAvatarUrl());
        modelMap.put("gender", topic.getGender());
        modelMap.put("province", topic.getProvince());
        modelMap.put("city", topic.getCity());
        modelMap.put("country", topic.getCountry());

        logger.info("querytopic result: {}", modelMap);
        return modelMap;
    }

    /**
     * 根据tagNumber获取对应的话题
     * @param tagNumber
     * @return topicsList
     */
    @RequestMapping(value = "/gettopicsbytag", method = RequestMethod.GET)
    private ArrayList<Object> getTopicsByTagNumber(Integer tagNumber) {
        ArrayList<Object> topicsList = new ArrayList<Object>();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Topic> topics = new ArrayList<Topic>();
        //获取话题列表
        topics = topicService.getTopicsByTagNumber(tagNumber);
        ArrayList<HashMap> itemList = new ArrayList<HashMap>();
        for (Topic topic : topics) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("id", topic.getId());
            item.put("openId", topic.getOpenId());
            item.put("date", topic.getDate());
            item.put("title", topic.getTitle());
            item.put("content", topic.getContent());
            item.put("connect", topic.getConnect());
            item.put("tag", topic.getTag());
            item.put("nickName", topic.getNickName());
            item.put("avatarUrl", topic.getAvatarUrl());
            item.put("gender", topic.getGender());
            item.put("province", topic.getProvince());
            item.put("city", topic.getCity());
            item.put("country", topic.getCountry());
            itemList.add(item);
        }
        modelMap.put("item", itemList);
        modelMap.put("type", "tagTopics");
        topicsList.add(modelMap);

        logger.info("gettopics result: {}", topicsList);
        return topicsList;
    }

    /**
     * 添加话题
     * @param topic
     * @return
     */
    @RequestMapping(value = "/addtopic", method = RequestMethod.POST)
    private Map<String, Object> addDoc(@RequestBody Topic topic) throws JsonParseException, JsonMappingException, IOException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //添加话题
        modelMap.put("success", topicService.addTopic(topic));

        logger.info("add topic: {}", topic.toString());
        return modelMap;
    }
}
