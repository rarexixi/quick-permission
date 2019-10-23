package org.xi.quick.common.service;

import org.xi.quick.common.model.*;
import org.xi.quick.common.model.OrderCondition;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.model.PageInfoVo;

import java.util.List;

public interface BaseService<AddOrEditVm, DetailVm, OrderVm extends OrderCondition, SearchVm> {

    /**
     * 添加
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int add(AddOrEditVm vm);

    /**
     * 根据条件删除
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int delete(SearchVm searchVm);

    /**
     * 根据条件获取实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    DetailVm get(SearchVm searchVm);

    /**
     * 获取列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    List<DetailVm> getList(SearchVm search);

    /**
     * 获取列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    List<DetailVm> getList(OrderSearch<SearchVm, OrderVm> search);

    /**
     * 分页查询
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    PageInfoVo<DetailVm> getPageInfo(OrderSearchPage<SearchVm, OrderVm> searchPage);
}