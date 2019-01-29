package cn.core.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseObj implements Serializable {
    private Integer creatorId;
    private Date creatorTime;
    private Integer modifierId;
    private Date modifierTime;
    private Integer deleteId;
    private Date deleteTime;

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
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

    public void setModifierId(Integer modifierId) {
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

    public void setDeleteId(Integer deleteId) {
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
