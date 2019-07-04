package org.b3log.java.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author : yu.zhang
 * @date : 2019-06-14 11:21
 * Email : zephyrjung@126.com
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestObject {
    private String test;

    public static void main(String[] args) {
        TestObject testObject1 = new TestObject("aaaaaabbbbbbb");
        TestObject testObject2 = new TestObject("aaaaaabbbbbbb2");
        TestObject testObject3 = new TestObject("aaaaaabbbbbbb3");
        TestObject[] testArrays = new TestObject[]{testObject3, testObject2, testObject1};
        Arrays.sort(testArrays, new Comparator<TestObject>() {
            @Override
            public int compare(TestObject o1, TestObject o2) {
                return o1.getTest().compareTo(o2.test);
            }
        });
        System.out.println("test");
    }
}
