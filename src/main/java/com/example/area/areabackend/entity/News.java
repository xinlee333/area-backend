package com.example.area.areabackend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {
    //主键
    private int id;
    //标题
    private String title;
    //来源
    private String source;
    //链接
    private String url;
    //更新时间
    private String updateTime;
    //点赞
    private String likes;
    //点赞图标
    private String likeIcon;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", likes=" + likes +
                ", likeIcon='" + likeIcon + '\'' +
                '}';
    }
}
