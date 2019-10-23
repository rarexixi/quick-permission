package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.PermissionCondition;
import org.xi.quick.sys.models.condition.extension.PermissionConditionExtension;
import org.xi.quick.sys.models.condition.order.PermissionOrderCondition;
import org.xi.quick.sys.models.entity.PermissionEntity;
import org.xi.quick.sys.models.entity.extension.PermissionEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionEntity, PermissionCondition> {

    /**
     * 根据主键获取
     *
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    PermissionEntityExtension getByPk(@Param("permissionId") Integer permissionId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<PermissionEntityExtension> getExList(@Param("condition") PermissionConditionExtension condition, @Param("order") PermissionOrderCondition order);
}
