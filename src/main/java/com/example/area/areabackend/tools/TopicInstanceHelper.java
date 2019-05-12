package com.example.area.areabackend.tools;

import com.example.area.areabackend.entity.Topic;

final public class TopicInstanceHelper {
    /**
     * topic实例中除id, gender, province, city, country外是否有空变量
     * @param topic
     * @return
     */
    public static boolean isAnyVariableNull(Topic topic) {
        boolean isVariableNull = true;
        if (topic != null) {
            //需要打日志
            if (topic.getOpenId() == null || "".equals(topic.getOpenId())) {
                isVariableNull = false;
            }
            if (topic.getDate() == null || "".equals(topic.getDate())) {
                isVariableNull = false;
            }
            if (topic.getTitle() == null || "".equals(topic.getTitle())) {
                isVariableNull = false;
            }
            if (topic.getContent() == null || "".equals(topic.getContent())) {
                isVariableNull = false;
            }
            if (topic.getTag() == null || "".equals(topic.getTag())) {
                isVariableNull = false;
            }
            if (topic.getNickName() == null || "".equals(topic.getNickName())) {
                isVariableNull = false;
            }
            if (topic.getAvatarUrl() == null || "".equals(topic.getAvatarUrl())) {
                isVariableNull = false;
            }
            return isVariableNull;
        } else {
            return false;
        }
    }
}
