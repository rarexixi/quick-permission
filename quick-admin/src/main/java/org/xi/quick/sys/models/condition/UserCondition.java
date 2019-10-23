package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

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
public class UserCondition extends BaseCondition {

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
     * 密码
     */
    private String password;

    /**
     * 密码 列表
     */
    private List<String> passwordList;

    /**
     * 密码为空
     */
    private Boolean passwordIsEmpty;

    /**
     * 密码
     */
    private String passwordStartWith;

    /**
     * 密码
     */
    private String passwordContains;

    /**
     * 盐
     */
    private String salt;

    /**
     * 盐 列表
     */
    private List<String> saltList;

    /**
     * 盐为空
     */
    private Boolean saltIsEmpty;

    /**
     * 盐
     */
    private String saltStartWith;

    /**
     * 盐
     */
    private String saltContains;

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
     * 头像
     */
    private String avatar;

    /**
     * 头像 列表
     */
    private List<String> avatarList;

    /**
     * 头像为空
     */
    private Boolean avatarIsEmpty;

    /**
     * 头像
     */
    private String avatarStartWith;

    /**
     * 头像
     */
    private String avatarContains;
}
