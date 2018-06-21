package org.b3log.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : yu.zhang
 * Date : 2018/6/16 上午9:33
 * Email : yu.zhang@7fresh.com
 **/
public class P811_SubdomainVisitCount {
    public static void main(String[] args) {
        System.out.println(subdomainVisits(new String[]{"9001 discuss.leetcode.com"}));
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> domainMap = new HashMap<>();
        for (String s : cpdomains) {
            String domain = s.split(" ")[1];
            int count = Integer.valueOf(s.split(" ")[0]);
            domainMap.merge(domain, count, (a, b) -> a + b);
            while (s.contains(".")) {
                s = s.substring(s.indexOf(".") + 1);
                domainMap.merge(s, count, (a, b) -> a + b);
            }
        }
        return domainMap.keySet().stream().map(d -> domainMap.get(d) + " " + d).collect(Collectors.toList());
    }
}
