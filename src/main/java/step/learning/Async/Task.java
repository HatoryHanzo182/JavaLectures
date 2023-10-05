package step.learning.Async;

import java.util.SortedMap;
import java.util.concurrent.*;

public class Task
{
    public void Run()
    {
        long t = System.nanoTime();
        Callable<String> callable = new Callable<String>()
        {
            @Override
            public String call() throws Exception
            {
                Thread.sleep(1000);
                return "Callable finishes";
            }
        };

        Future<String> task =  thread_pool.submit(callable);

        System.out.printf("%.1f: %s\n", (System.nanoTime() - t) / 1e6, task);

        Future<String> task2 =  thread_pool.submit(() ->
        {
            Thread.sleep(500);
            return "Task 2 finishes";
        });

        try
        {
            System.out.printf("%.1f: %s\n", (System.nanoTime() - t) / 1e6, task2.get());

            String res = task.get();

            System.out.printf("%.1f: %s\n", (System.nanoTime() - t) / 1e6, res);
        }
        catch (InterruptedException ex) { throw new RuntimeException(ex); }
        catch (ExecutionException ex) { throw new RuntimeException(ex); }

        thread_pool.shutdown();
        System.out.printf("%.1f: Main finishes!\n", (System.nanoTime() - t) / 1e6);
    }





















    //
    // + + + + + + + + + + Run1 + + + + + + + + + +
    private final ExecutorService thread_pool = Executors.newFixedThreadPool(3);
    public void Run1()
    {
        long t = System.nanoTime();

        thread_pool.submit(() ->
        {
            System.out.printf("%.1f: Tasks start!\n", (System.nanoTime() - t) / 1e6);

            try { Thread.sleep(1000); }
            catch (InterruptedException ex) { System.err.println(ex.getMessage()); }

            System.out.printf("%.1f: Tasks finishes!\n", (System.nanoTime() - t) / 1e6);
        });

        try { Thread.sleep(500); }
        catch (InterruptedException ex) { System.err.println(ex.getMessage()); }

        System.out.printf("%.1f: shutdown\n", (System.nanoTime() - t) / 1e6);
        thread_pool.shutdown();
        System.out.printf("%.1f: Main finishes!\n", (System.nanoTime() - t) / 1e6);
    }
    // + + + + + + + + + + + + + + + + + + + + + + +
}