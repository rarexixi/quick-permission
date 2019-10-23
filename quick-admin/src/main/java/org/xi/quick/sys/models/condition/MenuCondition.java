package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class MenuCondition extends BaseCondition {

    /**
     * 按钮ID
     */
    private Integer menuId;

    /**
     * 按钮ID 列表
     */
    private List<Integer> menuIdList;

    /**
     * 最小 按钮ID
     */
    private Integer menuIdMin;

    /**
     * 最大 按钮ID
     */
    private Integer menuIdMax;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;

    /**
     * 父菜单ID 列表
     */
    private List<Integer> parentIdList;

    /**
     * 最小 父菜单ID
     */
    private Integer parentIdMin;

    /**
     * 最大 父菜单ID
     */
    private Integer parentIdMax;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单编码 列表
     */
    private List<String> menuCodeList;

    /**
     * 菜单编码为空
     */
    private Boolean menuCodeIsEmpty;

    /**
     * 菜单编码
     */
    private String menuCodeStartWith;

    /**
     * 菜单编码
     */
    private String menuCodeContains;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单名称 列表
     */
    private List<String> menuNameList;

    /**
     * 菜单名称为空
     */
    private Boolean menuNameIsEmpty;

    /**
     * 菜单名称
     */
    private String menuNameStartWith;

    /**
     * 菜单名称
     */
    private String menuNameContains;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 菜单URL 列表
     */
    private List<String> urlList;

    /**
     * 菜单URL为空
     */
    private Boolean urlIsEmpty;

    /**
     * 菜单URL
     */
    private String urlStartWith;

    /**
     * 菜单URL
     */
    private String urlContains;

    /**
     * 类型 (0目录,1菜单)
     */
    private Integer type;

    /**
     * 类型 列表
     */
    private List<Integer> typeList;

    /**
     * 最小 类型
     */
    private Integer typeMin;

    /**
     * 最大 类型
     */
    private Integer typeMax;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单图标 列表
     */
    private List<String> iconList;

    /**
     * 菜单图标为空
     */
    private Boolean iconIsEmpty;

    /**
     * 菜单图标
     */
    private String iconStartWith;

    /**
     * 菜单图标
     */
    private String iconContains;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 排序 列表
     */
    private List<Integer> sortList;

    /**
     * 最小 排序
     */
    private Integer sortMin;

    /**
     * 最大 排序
     */
    private Integer sortMax;
}
