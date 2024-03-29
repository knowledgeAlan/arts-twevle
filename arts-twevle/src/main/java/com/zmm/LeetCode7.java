package com.zmm;

/**
 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

 示例 1:

 输入: 123
 输出: 321
  示例 2:

 输入: -123
 输出: -321
 示例 3:

 输入: 120
 输出: 21
 注意:

 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。



 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-integer

 * </pre>
 */
public class LeetCode7 {

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }

    public static int reverse(int x){

        int recv = 0;
        while (x!=0){


            int pop = x % 10;
            x /= 10;
            if (recv > Integer.MAX_VALUE / 10 ||
                    (recv == Integer.MAX_VALUE / 10 && pop > 7)){
                return 0;
            }

            if (recv < Integer.MIN_VALUE / 10 ||
                    (recv == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;
            }
            recv = recv * 10 + pop;
        }
        return recv;
    }
}
