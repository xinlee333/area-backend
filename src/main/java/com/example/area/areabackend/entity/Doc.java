package com.example.area.areabackend.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Doc {
    //自增主键
    private Integer id;
    //唯一区分用户的标识
    private String openId;
    //区分作业/备忘录的标识,值为创建时间毫秒数值
    private String docId;
    //日期信息
    private String date;
    //标题
    private String title;
    //内容
    private String content;
    //图片存储的url
    private String image;
    //分类: 作业/备忘录
    private String category;
    //删除图片的url
    private String deleteUrl;

    @Override
    public String toString() {
        return "Doc{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", docId='" + docId + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", deleteUrl='" + deleteUrl + '\'' +
                '}';
    }
}
