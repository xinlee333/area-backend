package com.example.area.areabackend.dao;

import com.example.area.areabackend.entity.Doc;
import java.util.List;

public interface DocDao {
    /**
     * 列出该openId对应用户所有的作业/备忘录,并根据时间顺序(id记录插入顺序)排序
     * @param openId
     * @return docList
     */
    List<Doc> queryDocByOpenId(String openId);

    /**
     * 根据openId和docId获取对应的作业/备忘录记录
     * @param openId
     * @param docId
     * @return doc
     */
    Doc queryDoc(String openId, String docId);

    /**
     * 插入作业/备忘录信息
     * @param doc
     * @return id
     */
    int insertDoc(Doc doc);

    /**
     * 更新作业/备忘录信息
     * @param doc
     * @return 影响的行数
     */
    int updateDoc(Doc doc);

    /**
     * 删除作业/备忘录信息
     * @param openId
     * @param docId
     * @return 影响的行数
     */
    int deleteDoc(String openId, String docId);
}
