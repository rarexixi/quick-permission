package org.xi.quick.sys.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.xi.quick.common.model.*;
import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.OptionMapper;
import org.xi.quick.sys.models.condition.OptionCondition;
import org.xi.quick.sys.models.condition.order.OptionOrderCondition;
import org.xi.quick.sys.models.entity.OptionEntity;
import org.xi.quick.sys.models.condition.extension.OptionConditionExtension;
import org.xi.quick.sys.models.entity.extension.OptionEntityExtension;
import org.xi.quick.sys.service.OptionService;
import org.xi.quick.sys.vm.addoredit.OptionAddOrEditVm;
import org.xi.quick.sys.vm.detail.OptionDetailVm;
import org.xi.quick.sys.vm.order.OptionOrderVm;
import org.xi.quick.sys.vm.search.OptionSearchVm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("optionService")
@Transactional
public class OptionServiceImpl implements OptionService {

    @Autowired
    public OptionServiceImpl(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }

    private OptionMapper optionMapper;

    /**
     * 添加系统选项
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(OptionAddOrEditVm vm) {
        OptionEntity entity = vm.getOptionEntity();
        int count = optionMapper.insert(entity);
        vm.setOptionEntity(entity);
        return count;
    }

    /**
     * 根据条件删除系统选项
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(OptionSearchVm searchVm) {
        OptionCondition condition = searchVm.getCondition();
        return optionMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件禁用系统选项
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int disable(OptionSearchVm searchVm) {
        OptionCondition condition = searchVm.getCondition();
        OptionEntity entity = new OptionEntity();
        entity.setDeleted(1);
        return optionMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件启用系统选项
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int enable(OptionSearchVm searchVm) {
        OptionCondition condition = searchVm.getCondition();
        OptionEntity entity = new OptionEntity();
        entity.setDeleted(0);
        return optionMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件获取系统选项实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public OptionDetailVm get(OptionSearchVm searchVm) {
        OptionCondition condition = searchVm.getCondition();
        OptionEntity entity = optionMapper.getByCondition(condition);
        if (entity == null) return null;
        OptionDetailVm detailVm = new OptionDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键更新系统选项
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int update(OptionAddOrEditVm vm) {
        OptionCondition condition = new OptionCondition();
        condition.setId(vm.getId());
        return optionMapper.updateByCondition(vm.getOptionEntity(), condition);
    }

    /**
     * 根据主键获取系统选项详情
     *
     * @param id
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Cacheable(value = "option", key = "#id")
    @Override
    @Transactional(readOnly = true)
    public OptionDetailVm getDetail(Integer id) {
        OptionEntityExtension entity = optionMapper.getByPk(id);
        if (entity == null) return null;
        OptionDetailVm vm = new OptionDetailVm(entity);
        return vm;
    }

    /**
     * 获取系统选项列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<OptionDetailVm> getList(OptionSearchVm search) {
        if (search == null) return null;
        List<OptionEntityExtension> list = optionMapper.getExList(search.getConditionExtension(), null);
        List<OptionDetailVm> vmList = list.stream().map(OptionDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取系统选项列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<OptionDetailVm> getList(OrderSearch<OptionSearchVm, OptionOrderVm> search) {
        if (search == null) return null;
        OrderSearch<OptionConditionExtension, OptionOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<OptionEntityExtension> list = optionMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<OptionDetailVm> vmList = list.stream().map(OptionDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询系统选项
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<OptionDetailVm> getPageInfo(OrderSearchPage<OptionSearchVm, OptionOrderVm> searchPage) {

        OrderSearch<OptionConditionExtension, OptionOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<OptionEntityExtension> list = optionMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<OptionEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<OptionDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, OptionDetailVm::new);
        return pageInfoVo;
    }
}
