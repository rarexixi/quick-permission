package org.xi.quick.sys.models.entity.extension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.xi.quick.sys.models.entity.OptionEntity;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class OptionEntityExtension extends OptionEntity {

    /**
     * 类型 (0分组,1选项)
     */
    private String typeText;
}
