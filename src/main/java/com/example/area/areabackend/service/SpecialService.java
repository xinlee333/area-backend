package com.example.area.areabackend.service;

import com.example.area.areabackend.entity.Special;
import java.util.List;

public interface SpecialService {
    /**
     * 获取special列表
     * @return
     */
    List<Special> getSpecials();
}
