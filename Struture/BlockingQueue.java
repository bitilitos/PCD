package Struture;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private Queue<T> queue = new LinkedList<T>();
    private final int MAX_CAPACITY;

    public BlockingQueue() {
        MAX_CAPACITY = -1;
    }

    public BlockingQueue(int maxCapacity) {
        if (maxCapacity < 1) throw new IllegalArgumentException("maxCapacity must be a positive integer");
        MAX_CAPACITY = maxCapacity;
    }

    public synchronized void put(T obj) throws InterruptedException {
        if (!isInfinit()) {
            while (queue.size() == MAX_CAPACITY) {
                System.out.println(Thread.currentThread() + " is going to wait! Queue is full");
                wait();
            }
        }
        queue.offer(obj);
        System.out.println(Thread.currentThread() + " putted. Waiting Line = " + size());

        if (queue.size() == 1) notifyAll();

    }

    public synchronized T take() throws InterruptedException {
        while (queue.size() == 0) {
            System.out.println(Thread.currentThread() + " is going to wait! Queue is empty");
            wait();
        }

        if (queue.size() == MAX_CAPACITY && !isInfinit()) notifyAll();

        System.out.println(Thread.currentThread() + " took: " + (size()-1));
        return queue.poll();

    }

    public Boolean isInfinit() {
        if (MAX_CAPACITY == -1) return true;
        return false;
    }

    public int getMAX_CAPACITY() {
        return MAX_CAPACITY;
    }

    public int size() {
        return queue.size();
    }

    public synchronized void clear() {
        queue.clear();
    }


}
