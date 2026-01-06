package org.example.lambdaExpressions;

public class RunnableLambdaExpression {

    public static void main(String[] args) {
        //implementing runnable interface in traditional way

        Thread t1 = new Thread(new CustomRunnable());
        t1.start();

        // runnable interface implementing using lambda expression
        Runnable run = ()-> System.out.println("custom thread is running lambda expression .....!");
        Thread t2 = new Thread(run);
        t2.start();

        // more advanced
        Thread t3 = new Thread(()-> System.out.println("running tread using advanced lambda expression"));
        t3.start();


    }
}

class CustomRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("custom tread is running .....!");
    }
}
