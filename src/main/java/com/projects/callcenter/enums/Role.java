package com.projects.callcenter.enums;

public enum  Role {
    OPERATOR("O"),
    SUPERVISOR("S"),
    DIRECTOR("D");

    private String desc;

    Role(String desc){
        this.desc = desc;
    }
}
