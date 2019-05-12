package com.example.area.areabackend.web;

import com.example.area.areabackend.entity.Doc;
import com.example.area.areabackend.entity.QueryDoc;
import com.example.area.areabackend.service.DocService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/doc")
public class DocController {
    @Autowired
    private DocService docService;

    private static final Logger logger = LoggerFactory.getLogger(DocController.class);

    /**
     * 根据openId和docId获取对应的作业/备忘录记录
     *
     * @return doc其中content加密
     */
    @RequestMapping(value = "/querydoc", method = RequestMethod.POST)
    private Map<String, Object> getDocByOpenIdAndDocId(@RequestBody QueryDoc queryDoc) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Doc doc = docService.getDocByOpenIdAndDocId(queryDoc.getOpenId(), queryDoc.getDocId());
        modelMap.put("date", doc.getDate());
        modelMap.put("title", doc.getTitle());
        modelMap.put("content", doc.getContent());
        modelMap.put("image", doc.getImage());
        modelMap.put("category", doc.getCategory());
        modelMap.put("deleteUrl", doc.getDeleteUrl());

        logger.info("querydoc result: {}", modelMap);
        return modelMap;
    }

    /**
     * 添加作业/备忘录
     *
     * @return
     */
    @RequestMapping(value = "/adddoc", method = RequestMethod.POST)
    private Map<String, Object> addDoc(@RequestBody Doc doc) throws JsonParseException, JsonMappingException, IOException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //添加作业/备忘录
        modelMap.put("success", docService.addDoc(doc));

        logger.info("add doc: {}", doc.toString());
        return modelMap;
    }

    /**
     * 删除作业/备忘录
     *
     * @return
     */
    @RequestMapping(value = "/deletedoc", method = RequestMethod.POST)
    private Map<String, Object> deletedoc(@RequestBody QueryDoc queryDoc) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", docService.deleteDoc(queryDoc.getOpenId(), queryDoc.getDocId()));

        logger.info("delete doc: {}", queryDoc.toString());
        return modelMap;
    }

    /**
     * 修改作业/备忘录
     *
     * @return
     */
    @RequestMapping(value = "/modifydoc", method = RequestMethod.POST)
    private Map<String, Object> modifydoc(@RequestBody Doc doc) throws JsonParseException, JsonMappingException, IOException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", docService.modifyDoc(doc));

        logger.info("modify doc: {}", doc.toString());
        return modelMap;
    }
}
