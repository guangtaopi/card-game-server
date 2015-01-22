package com.silu.game.mq;


import java.util.ArrayList;
import java.util.List;

public class GroupVo {
    private String id;

    private String creator;

    private String avatarUrl;


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    private String name;

    private Integer number;

    private List<UserVo> members = new ArrayList<>();

    public Integer conversation;

    public Integer getConversation() {
        return conversation;
    }

    public void setConversation(Integer conversation) {
        this.conversation = conversation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserVo> getMembers() {
        return members;
    }

    public void setMembers(List<UserVo> members) {
        this.members = members;
    }
}
