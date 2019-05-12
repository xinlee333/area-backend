package com.example.area.areabackend.service.impl;

import com.example.area.areabackend.dao.SpecialDao;
import com.example.area.areabackend.entity.Special;
import com.example.area.areabackend.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecialServiceImpl implements SpecialService {
    @Autowired
    private SpecialDao specialDao;

    @Override
    public List<Special> getSpecials() {
        try {
            return specialDao.querySpecials();
        } catch (Exception e) {
            throw new RuntimeException("获取special列表失败:" + e.toString());
        }
    }
}
