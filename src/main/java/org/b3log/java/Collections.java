package org.b3log.java;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/12/24 10:36 AM
 * Email : yu.zhang@7fresh.com
 **/
public class Collections {
    public static void main(String[] args) {
        List<String> test = Lists.newArrayList("", "222");
        Set<String> ts = Sets.newHashSet("111");
        if (CollectionUtils.containsAny(test, ts)) {
            System.out.println(test.toString());
        } else {
            System.out.printf("nothing");
        }
    }
}
