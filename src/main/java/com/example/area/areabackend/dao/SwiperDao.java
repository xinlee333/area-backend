package com.example.area.areabackend.dao;

import com.example.area.areabackend.entity.Swiper;
import java.util.List;

public interface SwiperDao {
    /**
     * 获取swiper列表
     * @return
     */
    List<Swiper> querySwipers();
}
