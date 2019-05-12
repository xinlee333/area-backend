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
            logger.warn("获取话题列表失败: " + e.toString());
            throw new RuntimeException("获取话题列表失败:" + e.toString());
        }
    }

    @Override
    public Topic getTopicById(Integer id) {
        if (id != null) {
            try {
                return topicDao.queryTopicById(id);
            } catch (Exception e) {
                logger.warn("根据id: {}获取话题失败：{}", id, e.toString());
                throw new RuntimeException("根据id获取话题失败：" + e.toString());
            }
        } else {
            logger.warn("id: {}不能为空", id);
            throw new RuntimeException("id不能为空");
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
                    logger.warn("添加话题失败, topic: {}", topic);
                    throw new RuntimeException("添加话题失败");
                }
            } catch (Exception e) {
                logger.warn("添加话题失败, topic: {}, {}", topic, e.toString());
                throw new RuntimeException("添加话题失败:" + e.toString());
            }
        } else {
            logger.warn("话题信息不能为空, topic: {}", topic);
            throw new RuntimeException("话题信息不能为空");
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
                logger.warn("根据tagNumber: {}获取话题失败：{}", tagNumber, e.toString());
                throw new RuntimeException("根据tagNumber获取话题失败：" + e.toString());
            }
        } else {
            logger.warn("tagNumber: {}不能为空", tagNumber);
            throw new RuntimeException("tagNumber不能为空");
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
