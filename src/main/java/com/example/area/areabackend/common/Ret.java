package com.example.area.areabackend.common;

import java.util.HashMap;

public class Ret {
    private RetCode errCode;
    public HashMap<String, Object> info = new HashMap<>();

    public Ret(RetCode errCode) {
        this.errCode = errCode;
    }

    public Integer getErrCode() {
        return errCode.getMapping();
    }
}
