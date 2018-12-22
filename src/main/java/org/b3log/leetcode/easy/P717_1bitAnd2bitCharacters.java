package org.b3log.leetcode.easy;

import java.util.Arrays;

/**
 * @author : yu.zhang
 * Date : 2018/7/3 上午9:07
 * Email : zephyrjung@126.com
 **/
public class P717_1bitAnd2bitCharacters {
    public boolean isOneBitCharacter(int[] bits) {
        boolean result = false;
        return result;
    }

    private void process(int[] bits) {
        if (bits[0] == 0) {
            process(Arrays.copyOfRange(bits, 1, bits.length));
        }
        if (bits[0] == 1) {
            process(Arrays.copyOfRange(bits, 2, bits.length));
        }

    }

}
