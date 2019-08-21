package com.example.area.areabackend.service.impl;

import com.example.area.areabackend.dao.DocDao;
import com.example.area.areabackend.entity.Doc;
import com.example.area.areabackend.service.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.area.areabackend.tools.DocInstanceHelper;
import java.util.List;

@Service
public class DocServiceImpl implements DocService{
    @Autowired
    private DocDao docDao;

    private static final Logger logger = LoggerFactory.getLogger(DocServiceImpl.class);

    @Override
    public List<Doc> getDocsByOpenId(String openId) {
        if (openId != null && !"".equals(openId)) {
            try {
                return docDao.queryDocByOpenId(openId);
            } catch (Exception e) {
                //根据openId获取作业/备忘录列表失败
                logger.warn("Get homework/memorandum list wrong with openId: {}. {}.", openId, e.toString());
                throw new RuntimeException("Get homework/memorandum list wrong with openId:" + e.toString());
            }
        } else {
            //openId不能为空
            logger.warn("openId cannot be empty.");
            throw new RuntimeException("openId cannot be empty.");
        }
    }

    @Override
    public Doc getDocByOpenIdAndDocId(String openId, String docId) {
        if (openId != null && !"".equals(openId) && docId != null && !"".equals(docId)) {
            try {
                return docDao.queryDoc(openId, docId);
            } catch (Exception e) {
                //根据openId和docId获取作业/备忘录失败
                logger.warn("Get homework/memorandum wrong with openId: {} and docId: {}. {}.", openId, docId, e.toString());
                throw new RuntimeException("Get homework/memorandum wrong with openId and docId:" + e.toString());
            }
        } else {
            //openId和docId均不能为空
            logger.warn("openId: {} and docId:{} neither can be empty.", openId, docId);
            throw new RuntimeException("openId and docId neither can be empty.");
        }
    }

    @Transactional
    @Override
    public boolean addDoc(Doc doc){
        if (DocInstanceHelper.isAnyVariableNull(doc)) {
            try {
                int effectedNum = docDao.insertDoc(doc);
                if (effectedNum > 0) {
                    return true;
                } else {
                    //添加作业/备忘录失败
                    logger.warn("Add homework/memorandum wrong, doc: {}.", doc);
                    throw new RuntimeException("Add homework/memorandum wrong.");
                }
            } catch (Exception e) {
                //添加作业/备忘录失败
                logger.warn("Add homework/memorandum wrong, doc: {}. {}.", doc, e.toString());
                throw new RuntimeException("Add homework/memorandum wrong:" + e.toString());
            }
        } else {
            //作业/备忘录信息不能为空
            logger.warn("Homework/memorandum information cannot be empty, doc: {}.", doc);
            throw new RuntimeException("Homework/memorandum information cannot be empty.");
        }
    }

    @Transactional
    @Override
    public boolean modifyDoc(Doc doc){
        if (doc.getOpenId() != null && !"".equals(doc.getOpenId()) && doc.getDocId() != null && !"".equals(doc.getDocId())) {
            try {
                int effectedNum = docDao.updateDoc(doc);
                if (effectedNum > 0) {
                    return true;
                } else {
                    //更新作业/备忘录失败
                    logger.warn("Update homework/memorandum wrong, doc: {}.", doc);
                    throw new RuntimeException("更新作业/备忘录失败");
                }
            } catch (Exception e) {
                //更新作业/备忘录失败
                logger.warn("Update homework/memorandum wrong, doc: {}. {}.", doc, e.toString());
                throw new RuntimeException("Update homework/memorandum wrong:" + e.toString());
            }
        } else {
            //openId和docId均不能为空
            logger.warn("openId: {} and docId:{} neither can be empty.", doc.getOpenId(), doc.getDocId());
            throw new RuntimeException("openId and docId neither can be empty.");
        }
    }

    @Transactional
    @Override
    public boolean deleteDoc(String openId, String docId){
        if (openId != null && !"".equals(openId) && docId != null && !"".equals(docId)) {
            try {
                int effectedNum = docDao.deleteDoc(openId, docId);
                if (effectedNum > 0) {
                    return true;
                } else {
                    //删除作业/备忘录失败
                    logger.warn("Delete homework/memorandum wrong, openId: {}, docId: {}.", openId, docId);
                    throw new RuntimeException("Delete homework/memorandum wrong.");
                }
            } catch (Exception e) {
                //删除作业/备忘录失败
                logger.warn("Delete homework/memorandum wrong, openId: {}, docId: {}. {}.", openId, docId, e.toString());
                throw new RuntimeException("Delete homework/memorandum wrong:" + e.toString());
            }
        } else {
            //openId和docId均不能为空
            logger.warn("openId: {} and docId:{} neither can be empty.", openId, docId);
            throw new RuntimeException("openId and docId neither can be empty.");
        }
    }
}
