package org.b3log.spring.core_technologies._3_7_Spring_Validation;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : yu.zhang
 * Date : 2018/10/9 下午2:44
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class PersonForm {
    @NotNull
    @Size(max=64)
    private String name;

    @Min(0)
    private int age;
}
