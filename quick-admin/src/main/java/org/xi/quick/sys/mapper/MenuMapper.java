package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.MenuCondition;
import org.xi.quick.sys.models.condition.extension.MenuConditionExtension;
import org.xi.quick.sys.models.condition.order.MenuOrderCondition;
import org.xi.quick.sys.models.entity.MenuEntity;
import org.xi.quick.sys.models.entity.extension.MenuEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity, MenuCondition> {

    /**
     * 根据主键获取
     *
     * @param menuId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    MenuEntityExtension getByPk(@Param("menuId") Integer menuId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<MenuEntityExtension> getExList(@Param("condition") MenuConditionExtension condition, @Param("order") MenuOrderCondition order);
}
