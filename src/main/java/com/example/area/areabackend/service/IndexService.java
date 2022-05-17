package com.example.area.areabackend.service;

import com.example.area.areabackend.common.Ret;

public interface IndexService {
    Ret signUp(String username, String password);
    Ret signIn(String username, String password, String gameTag);
    Ret saveScore(String token, String gameTag, int level, int score);
}
