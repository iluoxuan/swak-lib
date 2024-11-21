package com.swak.lib.common.test.ognl;

/**
 * @author: ljq
 * @date: 2024/11/10
 */
public class OgnlTest {


    public static boolean isHuiWen(int input) {
        if (input < 0) {
            return false;
        }
        String inputStr = String.valueOf(input);
        int left = 0;
        int right = inputStr.length() - 1;
        while (left < right) {
            if (inputStr.charAt(left) != inputStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(isHuiWen(11211));

    }


}
