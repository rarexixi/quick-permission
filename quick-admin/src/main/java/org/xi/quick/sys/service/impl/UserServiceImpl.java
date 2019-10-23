package org.xi.quick.sys.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.common.utils.security.CryptoUtils;
import org.xi.quick.configuration.properties.WebProperties;
import org.xi.quick.sys.mapper.UserMapper;
import org.xi.quick.sys.models.condition.UserCondition;
import org.xi.quick.sys.models.condition.order.UserOrderCondition;
import org.xi.quick.sys.models.entity.UserEntity;
import org.xi.quick.sys.models.condition.extension.UserConditionExtension;
import org.xi.quick.sys.models.entity.extension.UserEntityExtension;
import org.xi.quick.sys.service.*;
import org.xi.quick.sys.vm.addoredit.UserAddOrEditVm;
import org.xi.quick.sys.vm.detail.MenuDetailVm;
import org.xi.quick.sys.vm.detail.RoleMenuDetailVm;
import org.xi.quick.sys.vm.detail.UserDetailVm;
import org.xi.quick.sys.vm.detail.UserRoleDetailVm;
import org.xi.quick.sys.vm.order.UserOrderVm;
import org.xi.quick.sys.vm.search.MenuSearchVm;
import org.xi.quick.sys.vm.search.RoleMenuSearchVm;
import org.xi.quick.sys.vm.search.UserRoleSearchVm;
import org.xi.quick.sys.vm.search.UserSearchVm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.model.PageInfoVo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleService roleService, UserRoleService userRoleService,
                           RoleMenuService roleMenuService, MenuService menuService, WebProperties webProperties) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.roleMenuService = roleMenuService;
        this.menuService = menuService;
        this.webProperties = webProperties;
    }

    private final UserMapper userMapper;
    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final RoleMenuService roleMenuService;
    private final MenuService menuService;
    private final WebProperties webProperties;

    /**
     * 添加系统用户
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(UserAddOrEditVm vm) {
        UserEntity entity = vm.getUserEntity();
        entity.setSalt(RandomStringUtils.randomAscii(16));
        int count = userMapper.insert(entity);
        vm.setUserEntity(entity);
        return count;
    }

    /**
     * 根据条件删除系统用户
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(UserSearchVm searchVm) {
        UserCondition condition = searchVm.getCondition();
        return userMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件禁用系统用户
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int disable(UserSearchVm searchVm) {
        UserCondition condition = searchVm.getCondition();
        UserEntity entity = new UserEntity();
        entity.setDeleted(1);
        return userMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件启用系统用户
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int enable(UserSearchVm searchVm) {
        UserCondition condition = searchVm.getCondition();
        UserEntity entity = new UserEntity();
        entity.setDeleted(0);
        return userMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件获取系统用户实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetailVm get(UserSearchVm searchVm) {
        UserCondition condition = searchVm.getCondition();
        UserEntity entity = userMapper.getByCondition(condition);
        if (entity == null) return null;
        UserDetailVm detailVm = new UserDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键更新系统用户
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int update(UserAddOrEditVm vm) {
        UserCondition condition = new UserCondition();
        condition.setUserId(vm.getUserId());
        vm.setPassword(null);
        return userMapper.updateByCondition(vm.getUserEntity(), condition);
    }

    /**
     * 根据主键更新密码
     *
     * @param userId
     * @param password
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int setPassword(Integer userId, String password) {

        UserCondition condition = new UserCondition();
        condition.setUserId(userId);

        UserEntity user = new UserEntity();
        String salt = RandomStringUtils.randomAscii(16);
        user.setSalt(salt);
        user.setPassword(CryptoUtils.getSHA256(password, salt));
        return userMapper.updateByCondition(user, condition);
    }

    /**
     * 根据主键获取系统用户详情
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Cacheable(value = "user", key = "#userId")
    @Override
    @Transactional(readOnly = true)
    public UserDetailVm getDetail(Integer userId) {
        UserEntityExtension entity = userMapper.getByPk(userId);
        if (entity == null) return null;
        UserDetailVm vm = new UserDetailVm(entity);
        return vm;
    }

    /**
     * 根据系统用户详情
     *
     * @param account
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "user", key = "#account")
    public UserDetailVm getUserAccount(String account) {
        UserEntity entity;
        UserCondition condition = new UserCondition();
        condition.setUsername(account);
        entity = userMapper.getByCondition(condition);
        if (entity == null) {
            condition = new UserCondition();
            condition.setEmail(account);
            entity = userMapper.getByCondition(condition);
        }
        if (entity == null) {
            condition = new UserCondition();
            condition.setMobile(account);
            entity = userMapper.getByCondition(condition);
        }
        if (entity == null) return null;
        UserDetailVm vm = new UserDetailVm(entity);
        return vm;
    }

    /**
     * 根据主键获取系统用户角色
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public Set<String> getUserRoles(Integer userId) {
        return userMapper.getRolesByUserId(userId);
    }

    /**
     * 根据主键获取系统用户权限
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public Set<String> getUserPermissions(Integer userId) {
        UserRoleSearchVm userRoleSearchVm = new UserRoleSearchVm();
        userRoleSearchVm.setUserId(userId);
        List<Integer> roleIdList = userRoleService.getRoleIdList(userId);
        return roleService.getRolesPermissions(roleIdList);
    }

    @Override
    public List<MenuDetailVm> getUserNavMenus(Integer userId) {
        Set<String> roles = getUserRoles(userId);
        MenuSearchVm menuSearchVm = new MenuSearchVm();
        menuSearchVm.setDeleted(0);
        // 如果是超级用户，返回所有菜单
        for (String role : roles) {
            if (webProperties.getRootUserRoles().contains(role)) {
                return menuService.getList(menuSearchVm);
            }
        }
        List<Integer> roleIdList = userRoleService.getRoleIdList(userId);
        if (roleIdList == null || roleIdList.isEmpty()) return null;

        Set<Integer> menuIdSet = new HashSet<>();
        for (Integer roleId : roleIdList) {
            List<Integer> list = roleMenuService.getMenuIdList(roleId);
            menuIdSet.addAll(list);
        }
        if (menuIdSet.isEmpty()) return null;
        List<Integer> menuIdList = new ArrayList<>(menuIdSet);

        menuSearchVm.setMenuIdList(menuIdList);
        return menuService.getList(menuSearchVm);
    }

    /**
     * 获取系统用户列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDetailVm> getList(UserSearchVm search) {
        if (search == null) return null;
        List<UserEntityExtension> list = userMapper.getExList(search.getConditionExtension(), null);
        List<UserDetailVm> vmList = list.stream().map(UserDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取系统用户列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDetailVm> getList(OrderSearch<UserSearchVm, UserOrderVm> search) {
        if (search == null) return null;
        OrderSearch<UserConditionExtension, UserOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<UserEntityExtension> list = userMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<UserDetailVm> vmList = list.stream().map(UserDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询系统用户
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<UserDetailVm> getPageInfo(OrderSearchPage<UserSearchVm, UserOrderVm> searchPage) {

        OrderSearch<UserConditionExtension, UserOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<UserEntityExtension> list = userMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<UserEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<UserDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, UserDetailVm::new);
        return pageInfoVo;
    }
}
