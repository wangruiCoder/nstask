package cn.newstrength.core.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseBO implements Serializable {
    private Integer creatorId;
    private Date createTime;
    private Integer modifierId;
    private Date modifierTime;
    private Integer deleteId;
    private Date deleteTime;

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifierId() {
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

    public Integer getDeleteId() {
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
        sb.append(", creatorTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierTime=").append(modifierTime);
        sb.append(", deleteId=").append(deleteId);
        sb.append(", deleteTime=").append(deleteTime);
        return sb;
    }
}
