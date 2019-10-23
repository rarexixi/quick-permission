package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.UserTokenCondition;
import org.xi.quick.sys.models.condition.extension.UserTokenConditionExtension;
import org.xi.quick.sys.models.condition.order.UserTokenOrderCondition;
import org.xi.quick.sys.models.entity.UserTokenEntity;
import org.xi.quick.sys.models.entity.extension.UserTokenEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户Token
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface UserTokenMapper extends BaseMapper<UserTokenEntity, UserTokenCondition> {

    /**
     * 根据主键获取
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    UserTokenEntityExtension getByPk(@Param("userId") Integer userId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<UserTokenEntityExtension> getExList(@Param("condition") UserTokenConditionExtension condition, @Param("order") UserTokenOrderCondition order);

    /**
     * 替换插入
     *
     * @param entity
     * @return
     */
    int replaceInto(@Param("entity") UserTokenEntity entity);
}
