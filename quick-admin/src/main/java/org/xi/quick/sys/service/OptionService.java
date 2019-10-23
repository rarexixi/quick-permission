package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.OptionAddOrEditVm;
import org.xi.quick.sys.vm.detail.OptionDetailVm;
import org.xi.quick.sys.vm.order.OptionOrderVm;
import org.xi.quick.sys.vm.search.OptionSearchVm;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface OptionService extends BaseService<OptionAddOrEditVm, OptionDetailVm, OptionOrderVm, OptionSearchVm> {

    /**
     * 根据条件禁用系统选项
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int disable(OptionSearchVm searchVm);

    /**
     * 根据条件启用系统选项
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int enable(OptionSearchVm searchVm);

    /**
     * 根据主键更新系统选项
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int update(OptionAddOrEditVm vm);

    /**
     * 根据主键获取系统选项详情
     *
     * @param id
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    OptionDetailVm getDetail(Integer id);
}
