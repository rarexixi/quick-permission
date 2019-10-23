package org.xi.quick.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class BaseCondition implements Serializable {

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建人 列表
     */
    private List<Integer> createUserList;

    /**
     * 最小 创建人
     */
    private Integer createUserMin;

    /**
     * 最大 创建人
     */
    private Integer createUserMax;

    /**
     * 修改人
     */
    private Integer updateUser;

    /**
     * 修改人 列表
     */
    private List<Integer> updateUserList;

    /**
     * 最小 修改人
     */
    private Integer updateUserMin;

    /**
     * 最大 修改人
     */
    private Integer updateUserMax;

    /**
     * 创建时间 列表
     */
    private List<Date> createTimeList;

    /**
     * 最小 创建时间
     */
    private Date createTimeMin;

    /**
     * 最大 创建时间
     */
    private Date createTimeMax;

    /**
     * 更新时间 列表
     */
    private List<Date> updateTimeList;

    /**
     * 最小 更新时间
     */
    private Date updateTimeMin;

    /**
     * 最大 更新时间
     */
    private Date updateTimeMax;
}
