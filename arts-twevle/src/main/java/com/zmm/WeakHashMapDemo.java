package com.zmm;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description baipao
 * @encoding UTF-8
 * @date 2019/12/6
 * @time 13:42
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class WeakHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        Integer prime = 1;
        WeakReference<Integer> integerWeakReference = new WeakReference<>(prime);
        prime = null;
        System.out.println(integerWeakReference.get());
        System.gc();;


    }


}
