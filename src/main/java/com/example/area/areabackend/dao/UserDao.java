package com.example.area.areabackend.dao;

import com.example.area.areabackend.entity.DBInsertUser;
import com.example.area.areabackend.entity.DBQueryScore;
import com.example.area.areabackend.entity.DBSelectUser;

import java.util.List;

public interface UserDao {
    int insertUser(DBInsertUser insertUser);
    DBSelectUser selectUser(String username, String password);
}
