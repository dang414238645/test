package test.thread.deadlock;
/**
 * 既然可以上锁，那么假如有2个线程，一个线程想先锁对象1，再锁对象2，恰好另外有一个线程先锁对象2，再锁对象1。
在这个过程中，当线程1把对象1锁好以后，就想去锁对象2，但是不巧，线程2已经把对象2锁上了，也正在尝试去锁对象1。
 * @author changpengfei
 *
 */
public class TestDeadLock implements Runnable {
    public int flag = 1;
    static Object o1 = new Object(),o2 = new Object();
    public void run(){
        System.out.println("flag=" + flag);
        // 当flag==1锁住o1
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 只要锁住o2就完成
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        // 如果flag==0锁住o2
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 只要锁住o1就完成
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }
    public static void main(String[] args) {
        // 实例2个线程类
        TestDeadLock td1 = new TestDeadLock();
        TestDeadLock td2 = new TestDeadLock();
        td1.flag = 1;
        td2.flag = 0;
        // 开启2个线程
        Thread t1 = new Thread(td1);
        Thread t2 = new Thread(td2);
        t1.start();
        t2.start();
    }
}