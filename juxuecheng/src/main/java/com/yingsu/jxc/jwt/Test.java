package com.yingsu.jxc.jwt;

public class Test implements Runnable{

    private Thread t;
    private String threadName;

    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public static void main(String[] args){
        Thread t = new Thread(new Test(),"thread-11");
        t.start();
    }
}
