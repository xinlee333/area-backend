package com.example.area.areabackend.tools;

import com.example.area.areabackend.entity.Doc;

final public class DocInstanceHelper {
    /**
     * doc实例中除id外是否有空变量
     * @param doc
     * @return
     */
    public static boolean isAnyVariableNull(Doc doc) {
        boolean isVariableNull = true;
        if (doc != null) {
            //需要打日志
            if (doc.getOpenId() == null || "".equals(doc.getOpenId())) {
                isVariableNull = false;
            }
            if (doc.getDocId() == null || "".equals((doc.getDocId()))) {
                isVariableNull = false;
            }
            if (doc.getDate() == null || "".equals(doc.getDate())) {
                isVariableNull = false;
            }
            if (doc.getTitle() == null || "".equals(doc.getTitle())) {
                isVariableNull = false;
            }
            if (doc.getContent() == null || "".equals(doc.getContent())) {
                isVariableNull = false;
            }
            if (doc.getImage() == null || "".equals(doc.getImage())) {
                isVariableNull = false;
            }
            if (doc.getCategory() == null || "".equals(doc.getCategory())) {
                isVariableNull = false;
            }
            if (doc.getDeleteUrl() == null || "".equals(doc.getDeleteUrl())) {
                isVariableNull = false;
            }
            return isVariableNull;
        } else {
            return false;
        }
    }
}
