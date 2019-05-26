package com.example.area.areabackend.dao;

import com.example.area.areabackend.entity.News;
import java.util.List;

public interface NewsDao {
    /**
     * 获取news列表
     * @return
     */
    List<News> queryNews();
}
