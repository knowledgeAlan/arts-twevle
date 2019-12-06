package com.zmm;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description baipao
 * @encoding UTF-8
 * @date 2019-12-04
 * @time 13:37
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */

public class RWSample {
    private final Map<String, String> m = new TreeMap();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public String get(String key) {
        r.lock();
        System.out.println("读锁锁定！");
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    public String put(String key, String entry) {
        System.out.printf("正在执行等待获取所%s\n", Thread.currentThread().getName());
        w.lock();
        System.out.printf("写锁锁定%s\n",Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(10);
            return m.put(key, entry);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
            return "fail";
        }
    }


    public static void main(String[] args) {
        RWSample rwSample = new RWSample();
        String key = "key";
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                rwSample.put(key, Thread.currentThread().getName());
            }
        });

        thread1.setName("thread1");


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                rwSample.put(key, Thread.currentThread().getName());
            }
        });
        thread2.setName("thread2");

        thread1.start();
        thread2.start();
    }


}

