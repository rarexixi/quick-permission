package org.xi.quick.sys.service.impl;

import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.PermissionMapper;
import org.xi.quick.sys.models.condition.PermissionCondition;
import org.xi.quick.sys.models.condition.order.PermissionOrderCondition;
import org.xi.quick.sys.models.entity.PermissionEntity;
import org.xi.quick.sys.models.condition.extension.PermissionConditionExtension;
import org.xi.quick.sys.models.entity.extension.PermissionEntityExtension;
import org.xi.quick.sys.service.PermissionService;
import org.xi.quick.sys.vm.addoredit.PermissionAddOrEditVm;
import org.xi.quick.sys.vm.detail.PermissionDetailVm;
import org.xi.quick.sys.vm.order.PermissionOrderVm;
import org.xi.quick.sys.vm.search.PermissionSearchVm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.model.PageInfoVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    private PermissionMapper permissionMapper;

    /**
     * 添加权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(PermissionAddOrEditVm vm) {
        PermissionEntity entity = vm.getPermissionEntity();
        int count = permissionMapper.insert(entity);
        vm.setPermissionEntity(entity);
        return count;
    }

    /**
     * 根据条件删除权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(PermissionSearchVm searchVm) {
        PermissionCondition condition = searchVm.getCondition();
        return permissionMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件禁用权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int disable(PermissionSearchVm searchVm) {
        PermissionCondition condition = searchVm.getCondition();
        PermissionEntity entity = new PermissionEntity();
        entity.setDeleted(1);
        return permissionMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件启用权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int enable(PermissionSearchVm searchVm) {
        PermissionCondition condition = searchVm.getCondition();
        PermissionEntity entity = new PermissionEntity();
        entity.setDeleted(0);
        return permissionMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件获取权限实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PermissionDetailVm get(PermissionSearchVm searchVm) {
        PermissionCondition condition = searchVm.getCondition();
        PermissionEntity entity = permissionMapper.getByCondition(condition);
        if (entity == null) return null;
        PermissionDetailVm detailVm = new PermissionDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键更新权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int update(PermissionAddOrEditVm vm) {
        PermissionCondition condition = new PermissionCondition();
        condition.setPermissionId(vm.getPermissionId());
        return permissionMapper.updateByCondition(vm.getPermissionEntity(), condition);
    }

    /**
     * 根据主键获取权限详情
     *
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PermissionDetailVm getDetail(Integer permissionId) {
        PermissionEntityExtension entity = permissionMapper.getByPk(permissionId);
        if (entity == null) return null;
        PermissionDetailVm vm = new PermissionDetailVm(entity);
        return vm;
    }

    /**
     * 获取权限列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<PermissionDetailVm> getList(PermissionSearchVm search) {
        if (search == null) return null;
        List<PermissionEntityExtension> list = permissionMapper.getExList(search.getConditionExtension(), null);
        List<PermissionDetailVm> vmList = list.stream().map(PermissionDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取权限列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<PermissionDetailVm> getList(OrderSearch<PermissionSearchVm, PermissionOrderVm> search) {
        if (search == null) return null;
        OrderSearch<PermissionConditionExtension, PermissionOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<PermissionEntityExtension> list = permissionMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<PermissionDetailVm> vmList = list.stream().map(PermissionDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询权限
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<PermissionDetailVm> getPageInfo(OrderSearchPage<PermissionSearchVm, PermissionOrderVm> searchPage) {

        OrderSearch<PermissionConditionExtension, PermissionOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<PermissionEntityExtension> list = permissionMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<PermissionEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<PermissionDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, PermissionDetailVm::new);
        return pageInfoVo;
    }
}
