package org.b3log.performance.rxjava;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : yu.zhang
 * Date : 2018/10/17 下午12:01
 * Email : yu.zhang@7fresh.com
 **/
public class HelloWorld {
    public static void main(String[] args) {
        List<StringWrapper> test = Lists.newArrayList();
        test.add(new StringWrapper("1"));
        test.add(new StringWrapper("2"));
        test.add(new StringWrapper("3"));
        test.stream().map(HelloWorld::addStar).collect(Collectors.toList());
        for (StringWrapper stringWrapper : test) {
            System.out.println(stringWrapper.getValue());
        }
        System.out.println();
        StringWrapper test2 = new StringWrapper("4");
        addStar(test2);
        System.out.println(test2.getValue());
    }

    private static StringWrapper addStar(StringWrapper sw) {
        sw.setValue(sw.getValue() + "*");
        return sw;
    }

    @Data
    public static class StringWrapper {
        String value;

        public StringWrapper(String sw) {
            this.value = sw;
        }
    }
}
