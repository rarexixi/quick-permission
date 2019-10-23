package org.xi.quick.sys.models.entity.extension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.xi.quick.sys.models.entity.MenuEntity;

/**
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class MenuEntityExtension extends MenuEntity {

    /**
     * 类型 (0目录,1菜单)
     */
    private String typeText;
}
