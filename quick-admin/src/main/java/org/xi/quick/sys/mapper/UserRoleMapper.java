package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.UserRoleCondition;
import org.xi.quick.sys.models.condition.extension.UserRoleConditionExtension;
import org.xi.quick.sys.models.condition.order.UserRoleOrderCondition;
import org.xi.quick.sys.models.entity.UserRoleEntity;
import org.xi.quick.sys.models.entity.extension.UserRoleEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity, UserRoleCondition> {

    /**
     * 批量添加
     *
     * @param entityList
     * @return
     */
    int batchInsert(@Param("list") List<UserRoleEntity> entityList);

    /**
     * 根据主键获取
     *
     * @param userId
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    UserRoleEntityExtension getByPk(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<UserRoleEntityExtension> getExList(@Param("condition") UserRoleConditionExtension condition, @Param("order") UserRoleOrderCondition order);
}
