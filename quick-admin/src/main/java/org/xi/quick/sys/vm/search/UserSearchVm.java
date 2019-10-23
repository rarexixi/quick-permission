package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.UserCondition;
import org.xi.quick.sys.models.condition.extension.UserConditionExtension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserSearchVm implements SearchVm {

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户ID 列表
     */
    private List<Integer> userIdList;

    /**
     * 最小 用户ID
     */
    private Integer userIdMin;

    /**
     * 最大 用户ID
     */
    private Integer userIdMax;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户名 列表
     */
    private List<String> usernameList;

    /**
     * 用户名为空
     */
    private Boolean usernameIsEmpty;

    /**
     * 用户名
     */
    private String usernameStartWith;

    /**
     * 用户名
     */
    private String usernameContains;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮箱 列表
     */
    private List<String> emailList;

    /**
     * 邮箱为空
     */
    private Boolean emailIsEmpty;

    /**
     * 邮箱
     */
    private String emailStartWith;

    /**
     * 邮箱
     */
    private String emailContains;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 手机号 列表
     */
    private List<String> mobileList;

    /**
     * 手机号为空
     */
    private Boolean mobileIsEmpty;

    /**
     * 手机号
     */
    private String mobileStartWith;

    /**
     * 手机号
     */
    private String mobileContains;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 姓名 列表
     */
    private List<String> realNameList;

    /**
     * 姓名为空
     */
    private Boolean realNameIsEmpty;

    /**
     * 姓名
     */
    private String realNameStartWith;

    /**
     * 姓名
     */
    private String realNameContains;

    /**
     * 用户角色
     */
    private Integer roleId;

    public UserCondition getCondition() {

        return getConditionExtension();
    }

    public UserConditionExtension getConditionExtension() {

        UserConditionExtension condition = new UserConditionExtension();
        condition.setDeleted(this.deleted);
        condition.setUserId(this.userId);
        condition.setUserIdList(this.userIdList);
        condition.setUserIdMin(this.userIdMin);
        condition.setUserIdMax(this.userIdMax);
        condition.setUsername(this.username);
        condition.setUsernameList(this.usernameList);
        condition.setUsernameIsEmpty(this.usernameIsEmpty);
        condition.setUsernameStartWith(this.usernameStartWith);
        condition.setUsernameContains(this.usernameContains);
        condition.setEmail(this.email);
        condition.setEmailList(this.emailList);
        condition.setEmailIsEmpty(this.emailIsEmpty);
        condition.setEmailStartWith(this.emailStartWith);
        condition.setEmailContains(this.emailContains);
        condition.setMobile(this.mobile);
        condition.setMobileList(this.mobileList);
        condition.setMobileIsEmpty(this.mobileIsEmpty);
        condition.setMobileStartWith(this.mobileStartWith);
        condition.setMobileContains(this.mobileContains);
        condition.setRealName(this.realName);
        condition.setRealNameList(this.realNameList);
        condition.setRealNameIsEmpty(this.realNameIsEmpty);
        condition.setRealNameStartWith(this.realNameStartWith);
        condition.setRealNameContains(this.realNameContains);
        condition.setRoleId(this.roleId);
        return condition;
    }
}
