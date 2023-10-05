package step.learning.Async;

import java.util.SortedMap;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Task
{
    //
    // + + + + + + + + + + Run + + + + + + + + + +
    public void Run()
    {
        long t = System.nanoTime();
        Supplier<String> supplier = new Supplier<String>()
        {
            @Override
            public String get()
            {
                try { TimeUnit.MILLISECONDS.sleep(500); }
                catch (InterruptedException ex) { System.err.println("!SUPPLIER! (" + ex.getMessage() + ")"); }

                return "Supply of smith";
            }
        };
        Consumer<String> consumer = new Consumer<String>()
        {
            @Override
            public void accept(String s) { System.out.printf("%.1f: Accepted '%s'\n", (System.nanoTime() - t) / 1e6, s); }
        };
        Function<String, String> add_question = new Function<String, String>()
        {
            @Override
            public String apply(String s)
            {
                return s + "?";
            }
        };

        Future<String> task = CompletableFuture.supplyAsync(supplier, thread_pool);
        Future<?> task2 = CompletableFuture.supplyAsync(supplier, thread_pool).thenAccept(consumer);
        Future<String> task3 = CompletableFuture.supplyAsync(supplier, thread_pool).thenApply(add_question).thenApply(add_question);

        try
        {
            String res = task.get();

            System.out.printf("%.1f: %s\n", (System.nanoTime() - t) / 1e6, res);

            res = task3.get();

            System.out.printf("%.1f: %s\n", (System.nanoTime() - t) / 1e6, res);
        }
        catch(InterruptedException | ExecutionException ex) { System.err.println(ex.getMessage()); }

        thread_pool.shutdown();

        try
        {
            boolean is_done = thread_pool.awaitTermination(300, TimeUnit.MILLISECONDS);

            if(!is_done)
                thread_pool.shutdownNow();
        }
        catch (InterruptedException ex) { System.err.println(ex.getMessage()); }

        System.out.printf("%.1f: Main finished \n", (System.nanoTime() - t) / 1e6);
    }
    // + + + + + + + + + + + + + + + + + + + + + + +

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

    //
    // + + + + + + + + + + Run2 + + + + + + + + + +
    public void Run2()
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
            String res2 = task2.get();

            System.out.printf("%.1f: %s\n", (System.nanoTime() - t) / 1e6, res2);

            String res = task.get();

            System.out.printf("%.1f: %s\n", (System.nanoTime() - t) / 1e6, res);
        }
        catch (InterruptedException ex) { throw new RuntimeException(ex); }
        catch (ExecutionException ex) { throw new RuntimeException(ex); }

        thread_pool.shutdown();
        System.out.printf("%.1f: Main finishes!\n", (System.nanoTime() - t) / 1e6);
    }
    // + + + + + + + + + + + + + + + + + + + + + + +
}