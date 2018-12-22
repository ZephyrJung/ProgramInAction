package groovy.org.b3log.zephyr;

/**
 * @author : yu.zhang
 * Date : 2018/11/28 11:04 AM
 * Email : zephyrjung@126.com
 **/

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by liumengyan on 2017/6/5.
 */
@RunWith(Parameterized.class)
@Slf4j
public class JunitTest {

    private String name;
    private String family;
    private String fullName;

    public JunitTest(String name, String family, String fullName) {
        super();
        this.name = name;
        this.family = family;
        this.fullName = fullName;
    }

    @Parameterized.Parameters(name = "{2}")
    public static Collection input() {
        return Arrays.asList(new Object[][]{
                {"Bai", "Li", "Li Bai"},
                {"Ban", "Lu", "Lu Ban"},
                {"Fu", "Du", "Da Fu"}
        });
    }

    @Test
    public void test() {
        Assert.assertEquals(fullName, family + " " + name);
    }
}

