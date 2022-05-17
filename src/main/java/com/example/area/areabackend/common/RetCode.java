package com.example.area.areabackend.common;

public enum RetCode {
    SUCCESSED(0),
    TOKENERROR(-5),
    DBERROR(-10),
    DBDUPLICATE( -11),
    PARAMSINVALID(-100),
    ;

    private final Integer mapping;

    RetCode(Integer mapping) {
        this.mapping = mapping;
    }

    public Integer getMapping() {
        return mapping;
    }
}
