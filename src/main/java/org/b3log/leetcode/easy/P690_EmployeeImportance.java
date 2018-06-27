package org.b3log.leetcode.easy;

import com.google.common.collect.Lists;
import org.b3log.leetcode.common.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/6/27 下午5:18
 * Email : yu.zhang@7fresh.com
 **/
public class P690_EmployeeImportance {
    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return calcImportance(id, map);
    }

    private static int calcImportance(int id, Map<Integer, Employee> impMap) {
        Employee employee = impMap.get(id);
        int importance = employee.importance;
        for (int i : employee.subordinates) {
            importance += calcImportance(i, impMap);
        }
        return importance;
    }

    public static void main(String[] args) {
        System.out.println(getImportance(Lists.newArrayList(
                new Employee(1, 5, Lists.newArrayList(2, 3)),
                new Employee(2, 3, Lists.newArrayList()),
                new Employee(3, 3, Lists.newArrayList())
        ), 1));
    }
}
