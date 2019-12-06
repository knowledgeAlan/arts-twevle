package com.zmm;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description baipao
 * @encoding UTF-8
 * @date 2019-12-03
 * @time 21:23
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */

public class ThreadSafeSample {
    public int sharedState;
    public void nonSafeAction() {
//        System.out.printf("线程名称 == %s\n",Thread.currentThread().getName());
        while (sharedState < 100) {
            System.out.printf("开始%s\n",Thread.currentThread().getName());
            synchronized (this){
                System.out.printf("线程获取锁%s\n",Thread.currentThread().getName());
            int former = sharedState++;
            int latter = sharedState;
            if (former != latter - 1) {
                System.out.printf("Observed data race, former is " +
                        former + ", " + "latter is " + latter+Thread.currentThread().getName());
            }
        }}
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample sample = new ThreadSafeSample();
        Thread threadA = new Thread(){
            public void run(){
                sample.nonSafeAction();
            }
        };
        threadA.setName("threadA");
        Thread threadB = new Thread(){
            public void run(){
                sample.nonSafeAction();
            }
        };
        threadB.setName("threadB");
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}