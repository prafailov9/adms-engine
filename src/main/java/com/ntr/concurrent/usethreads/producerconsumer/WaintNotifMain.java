package com.ntr.concurrent.usethreads.producerconsumer;

public class WaintNotifMain {

    /**
     *  Guarded blocks prevent multiple threads of modifying shared memory at the same time.
     *  Such blocks will check for a particular condition before resuming execution
     *  wait() - suspends a thread
     *  notify() - wakes a thread up
     *
     *  wait() will put a Thread from Running state to Non-Runnable state.
     *  notify() will put a Thread from Non-runnable state to Runnable state.
     *
     *  To correctly implement this mechanism The Thread needs to have an object lock and both methods
     *  need to be invoked on that same object's lock.
     *
     *  notify() will wake up a random thread that is waiting for the object's lock.
     *  notifyAll() will wake up all threads.
     *  !!! before allowing the execution of a thread to continue - define a quick check for the condition required
     *  to proceed with the thread. This is because there are cases when a thread is woken up wiithout receiving a notification(will give an example)
     * @param args
     */

    public static void main(String[] args) throws InterruptedException {

        final ProdCons prodCons = new ProdCons(10);

        Thread prod = new Thread(prodCons::produce);
        Thread cons = new Thread(prodCons::consume);

        prod.start();
        cons.start();

        prod.join();
        cons.join();

    }

}
