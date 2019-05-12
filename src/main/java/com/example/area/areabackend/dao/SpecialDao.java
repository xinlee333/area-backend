package com.example.area.areabackend.dao;

import com.example.area.areabackend.entity.Special;
import java.util.List;

public interface SpecialDao {
    /**
     * 获取special列表
     * @return
     */
    List<Special> querySpecials();
}
