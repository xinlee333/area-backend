package com.example.area.areabackend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Special {
    //主键
    private int id;
    //标题
    private String title;
    //配图
    private String thumbnail;
    //类型
    private String type;
    //链接
    private String url;

    @Override
    public String toString() {
        return "Special{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
