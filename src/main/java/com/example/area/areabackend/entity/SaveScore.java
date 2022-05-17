package com.example.area.areabackend.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaveScore {
    private String token;
    private String gameTag;
    private int level;
    private int score;
}
