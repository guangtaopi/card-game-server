package com.silu.game.mq;


public class NotifyData {
    protected String type;
    protected NotifyInfo info;

    public NotifyData(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NotifyInfo getInfo() {
        return info;
    }

    public void setInfo(NotifyInfo info) {
        this.info = info;
    }
}

