package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.ConfigCondition;
import org.xi.quick.sys.models.condition.extension.ConfigConditionExtension;
import org.xi.quick.sys.models.condition.order.ConfigOrderCondition;
import org.xi.quick.sys.models.entity.ConfigEntity;
import org.xi.quick.sys.models.entity.extension.ConfigEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统配置
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface ConfigMapper extends BaseMapper<ConfigEntity, ConfigCondition> {

    /**
     * 根据主键获取
     *
     * @param key
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    ConfigEntityExtension getByPk(@Param("key") String key);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<ConfigEntityExtension> getExList(@Param("condition") ConfigConditionExtension condition, @Param("order") ConfigOrderCondition order);
}
