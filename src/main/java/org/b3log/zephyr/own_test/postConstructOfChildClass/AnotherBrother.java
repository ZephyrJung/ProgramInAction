package org.b3log.zephyr.own_test.postConstructOfChildClass;

import org.springframework.stereotype.Component;

/**
 * Brother 未复写Base的PostConstruct方法，其会执行Base中的print方法
 *
 * @author : yu.zhang
 * Date : 2018/11/12 7:51 PM
 * Email : zephyrjung@126.com
 **/
@Component
@Zephyr
public class AnotherBrother extends Base {
}
