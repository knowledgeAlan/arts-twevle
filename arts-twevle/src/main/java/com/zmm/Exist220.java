package com.zmm;

import java.util.TreeSet;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description baipao
 * @encoding UTF-8
 * @date 2019-11-13
 * @time 19:47
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class Exist220 {
    public static void main(String[] args) {
 //5


       // Math.sqrt(Math.pow((xg - xc) , 2) + Math.pow((yg - yc), 2))

        System.out.println(Math.pow(4,2));
        System.out.println(Math.sqrt(4));
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        TreeSet<Integer> treeSet = new TreeSet();
        for (int i = 0;i < nums.length;i++){
            Integer s = treeSet.ceiling(nums[i]);
            if ( null != s && s <= nums[i] + t){
                return Boolean.TRUE;
            }

            Integer g = treeSet.floor(nums[i]);
            if (null != g && nums[i] <= g + t){
                return Boolean.TRUE;
            }

            treeSet.add(nums[i]);

            if (treeSet.size() > k){
                treeSet.remove(nums[i - k]);
            }

        }

        return Boolean.FALSE;
    }
}
