package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.OptionCondition;
import org.xi.quick.sys.models.condition.extension.OptionConditionExtension;
import org.xi.quick.sys.models.condition.order.OptionOrderCondition;
import org.xi.quick.sys.models.entity.OptionEntity;
import org.xi.quick.sys.models.entity.extension.OptionEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface OptionMapper extends BaseMapper<OptionEntity, OptionCondition> {

    /**
     * 根据主键获取
     *
     * @param id
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    OptionEntityExtension getByPk(@Param("id") Integer id);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<OptionEntityExtension> getExList(@Param("condition") OptionConditionExtension condition, @Param("order") OptionOrderCondition order);
}
