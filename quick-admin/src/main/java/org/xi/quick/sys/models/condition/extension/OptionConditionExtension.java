package org.xi.quick.sys.models.condition.extension;

import org.xi.quick.sys.models.condition.OptionCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class OptionConditionExtension extends OptionCondition {

    /**
     * 分组
     */
    private String source;
}
