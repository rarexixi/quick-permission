package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.MenuCondition;
import org.xi.quick.sys.models.condition.extension.MenuConditionExtension;

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
public class MenuSearchVm implements SearchVm {

    /**
     * 是否删除
     */
    private Integer deleted;

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

    public void setCreateTimeRange(Date[] dateRange) {
        if (dateRange == null || dateRange.length != 2) return;
        this.createTimeMin = dateRange[0];
        this.createTimeMax = dateRange[1];
    }

    public void setUpdateTimeRange(Date[] dateRange) {
        if (dateRange == null || dateRange.length != 2) return;
        this.updateTimeMin = dateRange[0];
        this.updateTimeMax = dateRange[1];
    }

    public MenuCondition getCondition() {

        return getConditionExtension();
    }

    public MenuConditionExtension getConditionExtension() {

        MenuConditionExtension condition = new MenuConditionExtension();
        condition.setDeleted(this.deleted);
        condition.setMenuId(this.menuId);
        condition.setMenuIdList(this.menuIdList);
        condition.setMenuIdMin(this.menuIdMin);
        condition.setMenuIdMax(this.menuIdMax);
        condition.setSort(this.sort);
        condition.setSortList(this.sortList);
        condition.setSortMin(this.sortMin);
        condition.setSortMax(this.sortMax);
        condition.setCreateTimeList(this.createTimeList);
        condition.setCreateTimeMin(this.createTimeMin);
        condition.setCreateTimeMax(this.createTimeMax);
        condition.setUpdateTimeList(this.updateTimeList);
        condition.setUpdateTimeMin(this.updateTimeMin);
        condition.setUpdateTimeMax(this.updateTimeMax);
        return condition;
    }
}
