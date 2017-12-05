package edu.sla;

public class FibonacciLike implements Runnable {
    int amount;
    int first;
    int second;
    String sequenceString;

    FibonacciLike(int amountInSequence, int firstNum, int secondNum) {
        amount = amountInSequence;
        sequenceString = String.valueOf(firstNum) + " " + String.valueOf(secondNum);
        first = firstNum;
        second = secondNum;
    }

    // recursive method that generates Fibonacci sequence
    private void putNextNumberIntoSequence(int sequenceNum, int firstNum, int secondNum) {
        if (amount > 5) {
            // Yield this thread's processing time
            Thread.currentThread().yield();
        }
        if (sequenceNum < amount) {
            sequenceString = sequenceString + " " + String.valueOf(firstNum + secondNum);
            putNextNumberIntoSequence(sequenceNum + 1, secondNum, firstNum + secondNum);
        } else {
            // done putting numbers into sequence, let's print them all out
            System.out.println("Sequence with " + String.valueOf(amount) + " numbers: " + sequenceString);
        }
    }

    public void run() {
        // Prioritize the processing of the first Fibonacci sequence thread
        if (amount == 5) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        } else {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        }
        putNextNumberIntoSequence(2, first, second);
    }
}
