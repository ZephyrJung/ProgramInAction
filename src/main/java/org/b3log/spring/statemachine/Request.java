package org.b3log.spring.statemachine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : yu.zhang
 * @date : 2019-07-05 16:39
 * Email : zephyrjung@126.com
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    private String id;
    private Integer source;
}
