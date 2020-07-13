package org.b3log.java.exception;

/**
 * @author yu.zhang
 * @date 2020-06-05
 */
public class Test {
    public static void main(String[] args) {
        try {
            testError();
        }catch (Exception e){
            return;
        }finally{
            System.out.println("test 3");
        }
       testError2();
    }

    public static void testError() throws Exception {
        throw new RuntimeException("test 1");
    }

    public static void testError2() {
        throw new RuntimeException("test 2");
    }
}
