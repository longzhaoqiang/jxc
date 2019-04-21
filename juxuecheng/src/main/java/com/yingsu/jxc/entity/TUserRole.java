package com.yingsu.jxc.entity;

public class TUserRole {
    private Integer id;

    private String roleId;

    private String userId;

    private String operationRange;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOperationRange() {
        return operationRange;
    }

    public void setOperationRange(String operationRange) {
        this.operationRange = operationRange == null ? null : operationRange.trim();
    }
}