package com.silu.game.mq;

public class NotifyInfo {
    private UserVo user;
    private GroupVo group;
    private String desc;
    private String groupId;
    private String userId;

    private String msgType;
    private String from;


    public NotifyInfo() {
    }

    public NotifyInfo(String groupId, String userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    public NotifyInfo(String groupId, String userId, String desc) {
        this.desc = desc;
        this.groupId = groupId;
        this.userId = userId;
    }

    public NotifyInfo(GroupVo group, String desc) {
        this.group = group;
        this.desc = desc;
    }

    public NotifyInfo(UserVo user, String desc) {
        this.user = user;
        this.desc = desc;
    }

    public NotifyInfo(String desc, String from, String msgType, String userId) {
        this.desc = desc;
        this.msgType = msgType;
        this.from = from;
        this.userId = userId;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public GroupVo getGroup() {
        return group;
    }

    public void setGroup(GroupVo group) {
        this.group = group;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
