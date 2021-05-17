package personal.CP;

class MyThread extends Thread {
    int x;
    @Override
    public void run() {
        synchronized (this) {
            System.out.println("child-thread begins calculation");
            for (int i = 1; i <= 10; i++) {
                x += i;
            }
            System.out.println("child-thread sends notification");
            this.notify();
        }
    }

}


public class test4 {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        synchronized (t) {
            System.out.println("main-thread waits for child-thread");
            t.wait();
            System.out.println("main-thread receives notification");
            System.out.println(t.x);
        }
    }
}


