package com.example.area.areabackend.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryDoc {
    //唯一区分用户的标识
    private String openId;
    //区分作业/备忘录的标识,值为创建时间毫秒数值
    private String docId;

    @Override
    public String toString() {
        return "QueryDoc{" +
                "openId='" + openId + '\'' +
                ", docId='" + docId + '\'' +
                '}';
    }
}
