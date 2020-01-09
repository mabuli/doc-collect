package io.dfjx.module.fm.entity;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocTableMapping {
    private String title;
    private String column;
    private String value;
    private int linkPos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getLinkPos() {
        return linkPos;
    }

    public void setLinkPos(int linkPos) {
        this.linkPos = linkPos;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DocTableMapping(String title, String column, String value, int linkPos) {
        this.title = title;
        this.column = column;
        this.value = value;
        this.linkPos = linkPos;
    }

    public static Map<String, DocTableMapping> GetMapping(List<String> list){
        Map<String, DocTableMapping> maps = new HashMap<>();
        for(String str : list){
            String[] arr = StringUtils.split(str, "|");
            int pos = arr.length < 3 ? 0 : Integer.valueOf(arr[2]);
            DocTableMapping dtm = new DocTableMapping(arr[0], arr[1], null, pos);
            maps.put(arr[0], dtm);
        }
        return maps;
    }
}
