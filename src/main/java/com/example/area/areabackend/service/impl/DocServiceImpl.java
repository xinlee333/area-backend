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
                logger.warn("根据openId: {}获取作业/备忘录列表失败: {}", openId, e.toString());
                throw new RuntimeException("根据openId获取作业/备忘录列表失败:" + e.toString());
            }
        } else {
            logger.warn("openId不能为空");
            throw new RuntimeException("openId不能为空");
        }
    }

    @Override
    public Doc getDocByOpenIdAndDocId(String openId, String docId) {
        if (openId != null && !"".equals(openId) && docId != null && !"".equals(docId)) {
            try {
                return docDao.queryDoc(openId, docId);
            } catch (Exception e) {
                logger.warn("根据openId: {}和docId: {}获取作业/备忘录失败: {}", openId, docId, e.toString());
                throw new RuntimeException("根据openId和docId获取作业/备忘录失败:" + e.toString());
            }
        } else {
            logger.warn("openId: {}和docId: {}均不能为空", openId, docId);
            throw new RuntimeException("openId和docId不能为空");
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
                    logger.warn("添加作业/备忘录失败, doc: {}", doc);
                    throw new RuntimeException("添加作业/备忘录失败");
                }
            } catch (Exception e) {
                logger.warn("添加作业/备忘录失败, doc: {}, {}", doc, e.toString());
                throw new RuntimeException("添加作业/备忘录失败:" + e.toString());
            }
        } else {
            logger.warn("作业/备忘录信息不能为空, doc: {}", doc);
            throw new RuntimeException("作业/备忘录信息不能为空");
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
                    logger.warn("更新作业/备忘录失败, doc: {}", doc);
                    throw new RuntimeException("更新作业/备忘录失败");
                }
            } catch (Exception e) {
                logger.warn("更新作业/备忘录失败, doc: {}, {}", doc, e.toString());
                throw new RuntimeException("更新作业/备忘录失败:" + e.toString());
            }
        } else {
            logger.warn("openId: {}和docId: {}不能为空", doc.getOpenId(), doc.getDocId());
            throw new RuntimeException("openId和docId不能为空");
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
                    logger.warn("删除作业/备忘录失败, openId: {}, docId: {}", openId, docId);
                    throw new RuntimeException("删除作业/备忘录失败");
                }
            } catch (Exception e) {
                logger.warn("删除作业/备忘录失败, openId: {}, docId: {}, {}", openId, docId, e.toString());
                throw new RuntimeException("删除作业/备忘录失败:" + e.toString());
            }
        } else {
            logger.warn("openId: {}和docId: {}不能为空", openId, docId);
            throw new RuntimeException("openId和docId不能为空");
        }
    }
}
