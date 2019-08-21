package com.example.area.areabackend.service.impl;

import com.example.area.areabackend.dao.SwiperDao;
import com.example.area.areabackend.entity.Swiper;
import com.example.area.areabackend.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SwiperServiceImpl implements SwiperService {
    @Autowired
    private SwiperDao swiperDao;

    @Override
    public List<Swiper> getSwipers() {
        try {
            return swiperDao.querySwipers();
        } catch (Exception e) {
            //获取swiper列表失败
            throw new RuntimeException("Get swiper list wrong:" + e.toString());
        }
    }
}
