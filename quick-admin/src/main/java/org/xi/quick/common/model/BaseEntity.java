package org.xi.quick.common.model;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.validation.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * 
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

    public BaseEntity() {
    }

    public BaseEntity(BaseEntity entity) {
        if (entity == null) return;
        deleted = entity.getDeleted();
        createUser = entity.getCreateUser();
        updateUser = entity.getUpdateUser();
        createTime = entity.getCreateTime();
        updateTime = entity.getUpdateTime();
    }


    /**
     * 是否删除
     */
    @ExcelCol("是否删除")
    protected Integer deleted;

    /**
     * 创建人
     */
    @ExcelCol("创建人")
    protected Integer createUser;

    /**
     * 修改人
     */
    @ExcelCol("修改人")
    protected Integer updateUser;

    /**
     * 创建时间
     */
    @ExcelCol(value = "创建时间", formatter = "yyyy-MM-dd HH:mm")
    protected Date createTime;

    /**
     * 更新时间
     */
    @ExcelCol(value = "更新时间", formatter = "yyyy-MM-dd HH:mm")
    protected Date updateTime;

    protected void setTargetEntity(BaseEntity entity) {
        if (entity == null) return;
        entity.setDeleted(deleted);
        entity.setCreateUser(createUser);
        entity.setUpdateUser(updateUser);
        entity.setCreateTime(createTime);
        entity.setUpdateTime(updateTime);
    }

    protected void setCurrentEntity(BaseEntity entity) {
        if (entity == null) return;
        deleted = entity.getDeleted();
        createUser = entity.getCreateUser();
        updateUser = entity.getUpdateUser();
        createTime = entity.getCreateTime();
        updateTime = entity.getUpdateTime();
    }
}
