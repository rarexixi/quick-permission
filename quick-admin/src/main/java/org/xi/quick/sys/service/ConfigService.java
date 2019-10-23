package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.ConfigAddOrEditVm;
import org.xi.quick.sys.vm.detail.ConfigDetailVm;
import org.xi.quick.sys.vm.order.ConfigOrderVm;
import org.xi.quick.sys.vm.search.ConfigSearchVm;

/**
 * 系统配置
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface ConfigService extends BaseService<ConfigAddOrEditVm, ConfigDetailVm, ConfigOrderVm, ConfigSearchVm> {

    /**
     * 根据条件禁用系统配置
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int disable(ConfigSearchVm searchVm);

    /**
     * 根据条件启用系统配置
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int enable(ConfigSearchVm searchVm);

    /**
     * 根据主键更新系统配置
     *
     * @param vm
     * @param key
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int update(ConfigAddOrEditVm vm, String key);

    /**
     * 根据主键获取系统配置详情
     *
     * @param key
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    ConfigDetailVm getDetail(String key);
}
