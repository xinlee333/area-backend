package com.example.area.areabackend.service;

import com.example.area.areabackend.entity.Doc;
import java.util.List;

public interface DocService {
    /**
     * 通过openId获取作业/备忘录
     * @param openId
     * @return docList
     */
    List<Doc> getDocsByOpenId(String openId);

    /**
     * 根据openId和docId获取对应的作业/备忘录记录
     * @param openId
     * @param docId
     * @return doc
     */
    Doc getDocByOpenIdAndDocId(String openId, String docId);

    /**
     * 添加单条作业/备忘录记录
     * @param doc
     * @return 是否添加成功
     */
    boolean addDoc(Doc doc);

    /**
     * 更新单条作业/备忘录记录
     * @param doc
     * @return 是否更新成功
     */
    boolean modifyDoc(Doc doc);

    /**
     * 删除单条作业/备忘录
     * @param openId
     * @param docId
     * @return 是否删除成功
     */
    boolean deleteDoc(String openId, String docId);
}
