package org.b3log.leetcode.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/6/27 下午5:19
 * Email : zephyrjung@126.com
 **/
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
}
