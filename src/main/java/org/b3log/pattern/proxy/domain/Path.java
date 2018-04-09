package org.b3log.pattern.proxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 传输地址，相当于外部的输入信息
 * Created by Zephyr on 2017/1/6.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Path {
    private String start;
    private String end;
    private Date date;
}
