package com.example.area.areabackend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Swiper {
    //主键
    private int id;
    //标题
    private String title;
    //配图
    private String thumbnail;
    //来源
    private String source;
    //链接
    private String url;
    //更新时间
    private String updateTime;

    @Override
    public String toString() {
        return "Swiper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
