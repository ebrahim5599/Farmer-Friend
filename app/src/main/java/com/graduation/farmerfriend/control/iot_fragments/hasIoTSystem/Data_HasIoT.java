package com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem;

public class Data_HasIoT {

    private String path ;
    private String op ;
    private Boolean value ;

    public Data_HasIoT(String path, String op, Boolean value) {
        this.path = path;
        this.op = op;
        this.value = value;
    }

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

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
