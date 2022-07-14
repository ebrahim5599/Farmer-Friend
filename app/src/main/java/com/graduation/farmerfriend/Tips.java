package com.graduation.farmerfriend;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tips")
public class Tips {
    @PrimaryKey
    private int id ;

    private String content;
    private String header;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
