package com.example.area.areabackend.dao;

import com.example.area.areabackend.entity.DBInsertUser;
import com.example.area.areabackend.entity.DBQueryScore;
import com.example.area.areabackend.entity.DBSelectUser;

import java.util.List;

public interface ScoreDao {
    List<DBQueryScore> queryScore(int userId, String gameTag);
    int saveScore(int userId, String gameTag, int level, int score);
}
