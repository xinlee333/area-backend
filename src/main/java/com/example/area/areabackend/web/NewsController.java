package com.example.area.areabackend.web;

import com.example.area.areabackend.entity.News;
import com.example.area.areabackend.service.NewsService;
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
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
    /**
     * 列出所有新鲜事
     *
     * @return newsList
     */
    @RequestMapping(value = "/getnews", method = RequestMethod.GET)
    private ArrayList<Object> getTopics() {
        ArrayList<Object> newsList = new ArrayList<Object>();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<News> news = new ArrayList<News>();
        //获取新鲜事列表
        news = newsService.getNews();
        ArrayList<HashMap> itemList = new ArrayList<HashMap>();
        for (News piece : news) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("id", piece.getId());
            item.put("title", piece.getTitle());
            item.put("source", piece.getSource());
            item.put("url", piece.getUrl());
            item.put("updateTime", piece.getUpdateTime());
            item.put("likes", piece.getLikes());
            item.put("likeIcon", piece.getLikeIcon());
            itemList.add(item);
        }

        modelMap.put("item", itemList);
        modelMap.put("type", "news");
        newsList.add(modelMap);

        logger.info("getnews result: {}", newsList);
        return newsList;
    }
}
