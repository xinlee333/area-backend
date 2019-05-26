package com.example.area.areabackend.service.impl;

import com.example.area.areabackend.dao.NewsDao;
import com.example.area.areabackend.entity.News;
import com.example.area.areabackend.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsDao newsDao;

    @Override
    public List<News> getNews() {
        try {
            return newsDao.queryNews();
        } catch (Exception e) {
            throw new RuntimeException("获取news列表失败:" + e.toString());
        }
    }
}
