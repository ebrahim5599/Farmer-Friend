package com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem;

public class Data_HasIoT {

    private String path = "/hasIotSystem" ;
    private String op = "replace" ;
    public Boolean value ;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
