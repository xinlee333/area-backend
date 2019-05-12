package com.example.area.areabackend.dao;

import com.example.area.areabackend.entity.Topic;
import org.omg.CORBA.INTERNAL;

import java.util.List;

public interface TopicDao {
    /**
     * 列出所有的话题,并根据时间顺序(id记录插入顺序)排序
     * @param
     * @return topicList
     */
    List<Topic> queryTopics();

    /**
     * 根据id获取对应的话题
     * @param id
     * @return doc
     */
    Topic queryTopicById(Integer id);

    /**
     * 插入话题信息
     * @param topic
     * @return id
     */
    int insertTopic(Topic topic);

    /**
     * 更新话题信息（有待修改完善）
     * @param topic
     * @return 影响的行数
     */
    int updateTopic(Topic topic);

    /**
     * 删除话题信息
     * @param id
     * @return 影响的行数
     */
    int deleteTopic(Integer id);

    /**
     * 根据tag获取对应的话题
     * @param tag
     * @return topicList
     */
    List<Topic> queryTopicsByTag(String tag);
}
