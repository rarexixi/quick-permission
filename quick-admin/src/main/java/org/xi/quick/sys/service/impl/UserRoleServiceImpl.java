package org.xi.quick.sys.service.impl;

import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.UserRoleMapper;
import org.xi.quick.sys.models.condition.UserRoleCondition;
import org.xi.quick.sys.models.condition.order.UserRoleOrderCondition;
import org.xi.quick.sys.models.entity.UserRoleEntity;
import org.xi.quick.sys.models.condition.extension.UserRoleConditionExtension;
import org.xi.quick.sys.models.entity.extension.UserRoleEntityExtension;
import org.xi.quick.sys.service.UserRoleService;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.UserRoleAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.detail.UserRoleDetailVm;
import org.xi.quick.sys.vm.order.UserRoleOrderVm;
import org.xi.quick.sys.vm.search.UserRoleSearchVm;

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
 * 用户角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    private UserRoleMapper userRoleMapper;

    /**
     * 添加用户角色
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(UserRoleAddOrEditVm vm) {
        UserRoleEntity entity = vm.getUserRoleEntity();
        int count = userRoleMapper.insert(entity);
        vm.setUserRoleEntity(entity);
        return count;
    }

    /**
     * 根据条件删除用户角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(UserRoleSearchVm searchVm) {
        UserRoleCondition condition = searchVm.getCondition();
        return userRoleMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件获取用户角色实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public UserRoleDetailVm get(UserRoleSearchVm searchVm) {
        UserRoleCondition condition = searchVm.getCondition();
        UserRoleEntity entity = userRoleMapper.getByCondition(condition);
        if (entity == null) return null;
        UserRoleDetailVm detailVm = new UserRoleDetailVm(entity);
        return detailVm;
    }

    /**
     * 批量保存
     *
     * @param vm
     * @return
     */
    @Override
    public MtmRelationBatchResultVm batchSave(MtmRelationBatchSaveVm vm) {
        MtmRelationBatchResultVm result = new MtmRelationBatchResultVm();
        if (vm.getAddIdList() != null && !vm.getAddIdList().isEmpty()) {
            List<UserRoleEntity> addList = vm.getAddIdList().stream().map(item -> new UserRoleEntity(vm.getMainId(), item)).collect(Collectors.toList());
            result.setAdd(userRoleMapper.batchInsert(addList));
        }
        if (vm.getDelIdList() != null && !vm.getDelIdList().isEmpty()) {
            UserRoleCondition delCondition = new UserRoleCondition();
            delCondition.setUserId(vm.getMainId());
            delCondition.setRoleIdList(vm.getDelIdList());
            result.setAdd(userRoleMapper.deleteByCondition(delCondition));
        }
        return result;
    }

    @Override
    public List<Integer> getRoleIdList(Integer userId) {
        UserRoleCondition condition = new UserRoleCondition();
        condition.setUserId(userId);
        List<UserRoleEntity> list = userRoleMapper.selectByCondition(condition, null);
        return list.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * 根据主键获取用户角色详情
     *
     * @param userId
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public UserRoleDetailVm getDetail(Integer userId, Integer roleId) {
        UserRoleEntityExtension entity = userRoleMapper.getByPk(userId, roleId);
        if (entity == null) return null;
        UserRoleDetailVm vm = new UserRoleDetailVm(entity);
        return vm;
    }

    /**
     * 获取用户角色列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserRoleDetailVm> getList(UserRoleSearchVm search) {
        if (search == null) return null;
        List<UserRoleEntityExtension> list = userRoleMapper.getExList(search.getConditionExtension(), null);
        List<UserRoleDetailVm> vmList = list.stream().map(UserRoleDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取用户角色列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserRoleDetailVm> getList(OrderSearch<UserRoleSearchVm, UserRoleOrderVm> search) {
        if (search == null) return null;
        OrderSearch<UserRoleConditionExtension, UserRoleOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<UserRoleEntityExtension> list = userRoleMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<UserRoleDetailVm> vmList = list.stream().map(UserRoleDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询用户角色
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<UserRoleDetailVm> getPageInfo(OrderSearchPage<UserRoleSearchVm, UserRoleOrderVm> searchPage) {

        OrderSearch<UserRoleConditionExtension, UserRoleOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<UserRoleEntityExtension> list = userRoleMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<UserRoleEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<UserRoleDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, UserRoleDetailVm::new);
        return pageInfoVo;
    }
}
