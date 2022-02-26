package com.graduation.farmerfriend.IOTModels;

public class Control{
    public boolean fertSwitch;
    public boolean isAuto;
    public boolean waterSwitch;

    public boolean isFertSwitch() {
        return fertSwitch;
    }

    public void setFertSwitch(boolean fertSwitch) {
        this.fertSwitch = fertSwitch;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public boolean isWaterSwitch() {
        return waterSwitch;
    }

    public void setWaterSwitch(boolean waterSwitch) {
        this.waterSwitch = waterSwitch;
    }
}
