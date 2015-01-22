package com.silu.game.mq;

/**
 * Created by roger on 14-3-12.
 */
public class NotifyMessage {
    protected String type = "system";
    protected String from;
    protected String to;
    protected boolean ackFlag;
    protected boolean pushFlag;
    protected String pushContent;
    protected NotifyData data;
    protected int appId = 0;
    protected boolean incrOfflineCount = false;

    public boolean isIncrOfflineCount() {
        return incrOfflineCount;
    }

    public void setIncrOfflineCount(boolean incrOfflineCount) {
        this.incrOfflineCount = incrOfflineCount;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean getAckFlag() {
        return ackFlag;
    }

    public void setAckFlag(boolean ackFlag) {
        this.ackFlag = ackFlag;
    }

    public boolean getPushFlag() {
        return pushFlag;
    }

    public void setPushFlag(boolean pushFlag) {
        this.pushFlag = pushFlag;
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public NotifyData getData() {
        return data;
    }

    public void setData(NotifyData data) {
        this.data = data;
    }
}
