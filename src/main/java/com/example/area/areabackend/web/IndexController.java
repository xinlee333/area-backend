package com.example.area.areabackend.web;

import com.example.area.areabackend.entity.Doc;
import com.example.area.areabackend.entity.Special;
import com.example.area.areabackend.entity.Swiper;
import com.example.area.areabackend.service.DocService;
import com.example.area.areabackend.service.SpecialService;
import com.example.area.areabackend.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private DocService docService;

    @Autowired
    private SpecialService specialService;

    @Autowired
    private SwiperService swiperService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    /**
     * 提供首页数据
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private ArrayList<Object> postIndex(String openId) {
        ArrayList<Object> indexList = new ArrayList<Object>();
        Map<String, Object> docMap = listDocsByOpenId(openId);
        indexList.add(docMap);
        Map<String, Object> specialMap = listSpecials();
        indexList.add(specialMap);
        Map<String, Object> swiperMap = listSwipers();
        indexList.add(swiperMap);

        logger.info("indexList: {}", indexList);
        return indexList;
    }

    /**
     * 获取轮播图与标题
     *
     * @return
     */
    private Map<String, Object> listSwipers() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Swiper> swiperList = new ArrayList<Swiper>();
        //获取swiper列表
        swiperList = swiperService.getSwipers();
        ArrayList<HashMap> itemList = new ArrayList<HashMap>();
        for (Swiper swiper : swiperList) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("id", swiper.getId());
            item.put("title", swiper.getTitle());
            item.put("thumbnail", swiper.getThumbnail());
            item.put("source", swiper.getSource());
            item.put("url", swiper.getUrl());
            item.put("updateTime", swiper.getUpdateTime());
            itemList.add(item);
        }

        modelMap.put("item", itemList);
        modelMap.put("type", "focus");
        return modelMap;
    }

    /**
     * 获取专题栏目标题与图片
     *
     * @return
     */
    private Map<String, Object> listSpecials() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Special> specialList = new ArrayList<Special>();
        //获取special列表
        specialList = specialService.getSpecials();
        ArrayList<HashMap> itemList = new ArrayList<HashMap>();
        for (Special special : specialList) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("id", special.getId());
            item.put("title", special.getTitle());
            item.put("thumbnail", special.getThumbnail());
            item.put("type", special.getType());
            item.put("url", special.getUrl());
            itemList.add(item);
        }

        modelMap.put("item", itemList);
        modelMap.put("type", "secondnav");
        return modelMap;
    }

    /**
     * 根据openId获取作业/备忘录列表
     *
     * @param openId
     * @return
     */
    private Map<String, Object> listDocsByOpenId(String openId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int currentPage = 0;
        int totalPage = 0;
        List<Doc> docList = new ArrayList<Doc>();
        //获取doc列表
        docList = docService.getDocsByOpenId(openId);
        int docListSize = docList.size();
        if (docListSize == 0) {
            currentPage = 0;
            totalPage = 0;
        } else {
            ArrayList<HashMap> itemList = new ArrayList<HashMap>();
            for (Doc doc : docList) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("type", "doc");
                HashMap<String, Object> url = new HashMap<String, Object>();
                url.put("openId", doc.getOpenId());
                url.put("docId", doc.getDocId());
                item.put("id", doc.getId());
                item.put("link", url);
                item.put("date", doc.getDate());
                item.put("title", doc.getTitle());
                item.put("image", doc.getImage());
                item.put("category", doc.getCategory());
                itemList.add(item);
            }
            modelMap.put("item", itemList);
            currentPage = 1;
            if (docListSize <= 6) {
                totalPage = 1;
            } else {
                totalPage = docListSize/6;
            }
        }
        modelMap.put("currentPage", currentPage);
        modelMap.put("totalPage", totalPage);
        modelMap.put("type", "list");
        return modelMap;
    }

}
