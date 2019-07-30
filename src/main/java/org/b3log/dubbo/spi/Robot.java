package org.b3log.dubbo.spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author : yu.zhang
 * Date : 2019/3/6 2:08 PM
 * Email : zephyrjung@126.com
 **/
@SPI
public interface Robot {
    void sayHello();
}
