package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.CaptchaCondition;
import org.xi.quick.sys.models.condition.extension.CaptchaConditionExtension;
import org.xi.quick.sys.models.condition.order.CaptchaOrderCondition;
import org.xi.quick.sys.models.entity.CaptchaEntity;
import org.xi.quick.sys.models.entity.extension.CaptchaEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统验证码
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface CaptchaMapper extends BaseMapper<CaptchaEntity, CaptchaCondition> {

    /**
     * 根据主键获取
     *
     * @param uuid
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    CaptchaEntityExtension getByPk(@Param("uuid") String uuid);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<CaptchaEntityExtension> getExList(@Param("condition") CaptchaConditionExtension condition, @Param("order") CaptchaOrderCondition order);

    /**
     * 替换插入
     *
     * @param entity
     * @return
     */
    int replaceInto(@Param("entity") CaptchaEntity entity);
}
