package com.example.area.areabackend.service.impl;

import com.example.area.areabackend.dao.TopicDao;
import com.example.area.areabackend.entity.Topic;
import com.example.area.areabackend.service.TopicService;
import com.example.area.areabackend.tools.TopicInstanceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;

    private static final Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Override
    public List<Topic> getTopics() {
        try {
            return topicDao.queryTopics();
        } catch (Exception e) {
            //获取话题列表失败
            logger.warn("Get topic list wrong: " + e.toString());
            throw new RuntimeException("Get topic list wrong:" + e.toString());
        }
    }

    @Override
    public Topic getTopicById(Integer id) {
        if (id != null) {
            try {
                return topicDao.queryTopicById(id);
            } catch (Exception e) {
                //根据id获取话题失败
                logger.warn("Get topic wrong with id: {}. {}.", id, e.toString());
                throw new RuntimeException("Get topic wrong with id：" + e.toString());
            }
        } else {
            //id不能为空
            logger.warn("id: {} cannot be empty.", id);
            throw new RuntimeException("id cannot be empty.");
        }
    }

    @Transactional
    @Override
    public boolean addTopic(Topic topic) {
        if (TopicInstanceHelper.isAnyVariableNull(topic)) {
            try {
                int effectedNum = topicDao.insertTopic(topic);
                if (effectedNum > 0) {
                    return true;
                } else {
                    //添加话题失败
                    logger.warn("Add topic wrong, topic: {}.", topic);
                    throw new RuntimeException("Add topic wrong.");
                }
            } catch (Exception e) {
                //添加话题失败
                logger.warn("Add topic wrong, topic: {}. {}.", topic, e.toString());
                throw new RuntimeException("Add topic wrong:" + e.toString());
            }
        } else {
            //话题信息不能为空
            logger.warn("Topic information cannot be empty, topic: {}.", topic);
            throw new RuntimeException("Topic information cannot be empty.");
        }
    }

    @Override
    public List<Topic> getTopicsByTagNumber(Integer tagNumber) {
        if (tagNumber != null) {
            try {
                switch (tagNumber) {
                    case 0:
                        return topicDao.queryTopicsByTag("寻合租");
                    case 1:
                        return topicDao.queryTopicsByTag("失物招领");
                    case 2:
                        return topicDao.queryTopicsByTag("约跑步");
                    case 3:
                        return topicDao.queryTopicsByTag("一起学习");
                    case 4:
                        return topicDao.queryTopicsByTag("其他");
                    default:
                        return null;
                }
            } catch (Exception e) {
                //根据tagNumber获取话题失败
                logger.warn("Get topic wrong with tagNumber: {}. {}.", tagNumber, e.toString());
                throw new RuntimeException("Get topic wrong with tagNumber：" + e.toString());
            }
        } else {
            //tagNumber不能为空
            logger.warn("tagNumber: {} cannot be empty.", tagNumber);
            throw new RuntimeException("tagNumber cannot be empty.");
        }
    }

    /**
     * 更新话题
     *
     * @param topic
     * @return 是否更新成功
     */
    @Override
    public boolean modifyTopic(Topic topic) {
        return false;
    }

    /**
     * 删除话题
     *
     * @param id
     * @return 是否删除成功
     */
    @Override
    public boolean deleteTopic(Integer id) {
        return false;
    }
}
