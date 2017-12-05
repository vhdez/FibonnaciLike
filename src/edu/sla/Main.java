package edu.sla;

public class Main {

    public static void main(String[] args) {
        boolean concurrentMode = true;
        Thread[] myThreads = new Thread[5];

        // Let's print 10 Fibonacci-like sequences
        System.out.println("Here are 5 Fibonacci-like sequences.");
        for (int i = 0; i <= 4; i++) {
            int amountInSequence = 5 + i;       // 5 to 9
            int firstNumInSequence = -30 + (int) (Math.random() * 60);   // -30 to 30
            int secondNumInSequence = firstNumInSequence + (int) (Math.random() * 20); // firstNumInSequence to firstNumInSequence + 20
            FibonacciLike flsequence = new FibonacciLike(amountInSequence, firstNumInSequence, secondNumInSequence);
            if (!concurrentMode) {
                flsequence.run();
            } else {
                // create a new thread to print out this FibonacciLike sequence
                Thread thread = new Thread((Runnable) flsequence);
                myThreads[i] = thread;
                // tell that thread to print out this FibonacciLike sequence
                thread.start();
                // DO continue printing out more FibonacciLike sequences
            }
        }
        if (concurrentMode) {
            for (int i = 0; i <= 4; i++) {
                try {
                    // Get the Main thread to wait until all the other threads are done
                    myThreads[i].join();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        // Don't print this out until the other threads are done.
        System.out.println("Those were 5 Fibonacci-like sequences.");
    }
}
