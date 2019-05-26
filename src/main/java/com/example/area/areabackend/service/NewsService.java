package com.example.area.areabackend.service;

import com.example.area.areabackend.entity.News;
import java.util.List;

public interface NewsService {
    /**
     * 获取news列表
     * @return
     */
    List<News> getNews();
}
