package org.xi.quick.sys.vm.addoredit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.xi.quick.common.validation.DataAdd;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 多对多关系保存
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MtmRelationBatchSaveVm implements Serializable {

    /**
     * 主ID
     */
    @NotNull(groups = {DataAdd.class}, message = "menuId (菜单ID)不能为空")
    private Integer mainId;

    /**
     * 关联添加ID列表
     */
    private List<Integer> addIdList;
    /**
     * 关联删除ID列表
     */
    private List<Integer> delIdList;
}
