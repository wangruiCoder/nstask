package cn.core.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseObj implements Serializable {
    private int creatorId;
    private Date creatorTime;
    private int modifierId;
    private Date modifierTime;
    private int deleteId;
    private Date deleteTime;

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatorTime() {
        return creatorTime;
    }

    public void setCreatorTime(Date creatorTime) {
        this.creatorTime = creatorTime;
    }

    public int getModifierId() {
        return modifierId;
    }

    public void setModifierId(int modifierId) {
        this.modifierId = modifierId;
    }

    public Date getModifierTime() {
        return modifierTime;
    }

    public void setModifierTime(Date modifierTime) {
        this.modifierTime = modifierTime;
    }

    public int getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(int deleteId) {
        this.deleteId = deleteId;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    protected StringBuffer getBaseString(){
        final StringBuffer sb = new StringBuffer(",");
        sb.append("creatorId=").append(creatorId);
        sb.append(", creatorTime=").append(creatorTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierTime=").append(modifierTime);
        sb.append(", deleteId=").append(deleteId);
        sb.append(", deleteTime=").append(deleteTime);
        return sb;
    }
}
