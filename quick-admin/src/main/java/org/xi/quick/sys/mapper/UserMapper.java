package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.UserCondition;
import org.xi.quick.sys.models.condition.extension.UserConditionExtension;
import org.xi.quick.sys.models.condition.order.UserOrderCondition;
import org.xi.quick.sys.models.entity.UserEntity;
import org.xi.quick.sys.models.entity.extension.UserEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity, UserCondition> {

    /**
     * 根据主键获取
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    UserEntityExtension getByPk(@Param("userId") Integer userId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<UserEntityExtension> getExList(@Param("condition") UserConditionExtension condition, @Param("order") UserOrderCondition order);

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<String> getRolesByUserId(@Param("userId") Integer userId);
}
