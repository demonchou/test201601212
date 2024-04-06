package excise.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author hzzhouhongfei
 * @version $$ CoreThreadPool, 2023/5/17 17:14 hzzhouhongfei $$
 */
@Slf4j
public class CoreThreadPool implements Executor {

    // https://www.throwx.cn/2020/08/23/java-concurrency-thread-pool-executor/
    private BlockingQueue<Runnable> workQueue;
    private static final AtomicInteger COUNTER = new AtomicInteger();
    private int coreSize;
    private int threadCount = 0;

    public CoreThreadPool(int coreSize) {
        this.coreSize = coreSize;
        this.workQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void execute(Runnable command) {
        if (++threadCount <= coreSize) {
            new Worker(command).start();
        } else {
            try {
                workQueue.put(command);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class Worker extends Thread {
        private Runnable firstTask;

        public Worker(Runnable runnable) {
            super(String.format("Worker-%d", COUNTER.getAndIncrement()));
            this.firstTask = runnable;
        }

        @Override
        public void run() {
            Runnable task = this.firstTask;
            while (null != task || null != (task = getTask())) {
                try {
                    task.run();
                } finally {
                    task = null;
                }
            }
        }
    }

    private Runnable getTask() {
        try {
            return workQueue.take();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) throws Exception {
//        CoreThreadPool pool = new CoreThreadPool(5);
//        IntStream.range(0, 10).forEach(i -> pool.execute(() -> System.out.printf("Thread:%s,value:%d%n", Thread.currentThread().getName(), i)));
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 10, 50, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(3));
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.printf("Thread:%s%n", Thread.currentThread().getName());
            }
        });
//        for (int i = 0; i < 10; i++) {
//            pool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.printf("Thread:%s%n", Thread.currentThread().getName());
//                }
//            });
//        }
//        IntStream.range(0, 10).forEach(i -> pool.execute(() -> System.out.printf("Thread:%s,value:%d%n", Thread.currentThread().getName(), i)));

//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("=========================");
//                System.out.printf("ThreadPool Size: [%s]%n", pool.getPoolSize());
//                System.out.printf("Active Threads: {%s}%n", pool.getActiveCount());
//                System.out.printf("Number of Tasks : {%s}%n", pool.getCompletedTaskCount());
//                System.out.printf("Number of Tasks in Queue: {%s}%n", pool.getQueue().size());
//                System.out.println("=========================");
//            }
//        });
//        pool.shutdown();
        System.out.println("==========");
        System.out.println("==========");
        System.out.println("==========");
        System.out.println("==========");

    }
}