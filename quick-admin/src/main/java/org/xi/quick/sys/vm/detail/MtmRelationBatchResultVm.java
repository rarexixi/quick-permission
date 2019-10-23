package org.xi.quick.sys.vm.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MtmRelationBatchResultVm implements Serializable {
    private Integer add = 0;
    private Integer del = 0;
}
