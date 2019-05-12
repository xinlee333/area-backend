package com.example.area.areabackend.service;

import com.example.area.areabackend.entity.Topic;
import java.util.List;

public interface TopicService {
    /**
     * 列出所有的话题,并根据时间顺序(id记录插入顺序)排序
     *
     * @return topicList
     */
    List<Topic> getTopics();

    /**
     * 根据id获取对应的话题
     * @param id
     * @return topic
     */
    Topic getTopicById(Integer id);

    /**
     * 添加话题
     * @param topic
     * @return 是否添加成功
     */
    boolean addTopic(Topic topic);

    /**
     * 更新话题
     * @param topic
     * @return 是否更新成功
     */
    boolean modifyTopic(Topic topic);

    /**
     * 删除话题
     * @param id
     * @return 是否删除成功
     */
    boolean deleteTopic(Integer id);

    /**
     * 根据tag对应的数字获取对应的话题
     * @param tagNumber
     * @return topicList
     */
    List<Topic> getTopicsByTagNumber(Integer tagNumber);
}
