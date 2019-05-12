package com.example.area.areabackend.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Topic {
    //自增主键
    private int id;
    //唯一区分用户的标识
    private String openId;
    //日期
    private String date;
    //标题
    private String title;
    //描述
    private String content;
    //联系方式
    private String connect;
    //标签
    private String tag;
    //昵称
    private String nickName;
    //头像
    private String avatarUrl;
    //性别
    private String gender;
    //省份
    private String province;
    //城市
    private String city;
    //国家
    private String country;

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", connect='" + connect + '\'' +
                ", tag='" + tag + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gender='" + gender + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
