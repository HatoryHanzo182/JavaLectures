package step.learning.Async;

import java.awt.image.ImagingOpException;
import java.util.Random;

public class Async
{
    //
    // + + + + + + + + + + Run + + + + + + + + + +
    private String _constructed_number = "";  // Common string that threads will construct.
    private Object _locker = new Object();  // Monitor object to synchronize access to _constructed_number.

    class Pandigital implements Runnable  // This class is used to create 10 threads, each of which will execute the run() method,
    {                                    // which will generate a _constructed_number string using a different number added by each thread.
        private String _number;

        public Pandigital(String number)
        {
            _number = number;
        }

        @Override
        public void run()
        {
            try{ Thread.sleep(1000); }
            catch(InterruptedException ex) { System.err.println(ex.getMessage()); }

            synchronized (_locker)
            {
                _constructed_number += _number;
                System.out.println("New num: (" + _number + ")  |  result: (" + _constructed_number + ")");
            }
        }
    }

    public void Run()
    {
        Thread[] threads = new Thread[10];

        System.out.println("---------------------");

        for(int i = 0; i < 10; i++)
        {
            threads[i] = new Thread(new Pandigital(Integer.toString(new Random().nextInt(10))));
            threads[i].start();
        }

        try
        {
            for(int i = 0; i < 10; i++)
                threads[i].join();
        }
        catch(InterruptedException ex) { System.err.println(ex.getMessage()); }

        System.out.println("---------------------\nResult: { " + _constructed_number + " }");
    }
    // + + + + + + + + + + + + + + + + + + + + + + +

    //
    // + + + + + + + + + + Run1 + + + + + + + + + +
    public void Run1()
    {
        System.out.println("Async demo!");

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Thread 1(start)..");

                try { Thread.sleep(1000); }
                catch (InterruptedException ex) { System.err.println(ex.getMessage()); }

                System.err.println("Thread 1(finishes)..");
            }
        });
        thread1.start();

        try { thread1.join(); }
        catch (InterruptedException ex) { System.err.println(ex.getMessage()); }

        System.out.println("Async demo finishes");
    }
    // + + + + + + + + + + + + + + + + + + + + + + +

    //
    // + + + + + + + + + + Run2 + + + + + + + + + +
    private double _sum;
    private final Object _sum_locker = new Object();
    private final Object _atc_locker = new Object();
    private int _active_threads_count;

    class MontRate implements Runnable
    {
        int _month;

        public MontRate(int month)
        {
            this._month = month;
        }

        @Override
        public void run()
        {
            double percent = 10.0;
            double factor = (1.0 + percent / 100.0);
            double local_sum;

            synchronized (_sum_locker)
            {
                local_sum = _sum = _sum * factor;
            }

            System.out.printf("Month: %2d, percent: %.2f, sum: %.2f %n", this._month, percent, local_sum);

            boolean is_last;

            synchronized (_atc_locker)
            {
                _active_threads_count--;
                is_last = _active_threads_count == 0;

                try { Thread.sleep(1); }
                catch(InterruptedException ex) { System.err.println(ex.getMessage()); }

                if(is_last)
                    System.out.println("-------------------\nTotal: " + _sum);
            }
        }
    }

    public void Run2()
    {
        int monthes = 12;
        Thread[] threads = new Thread[monthes];

        _sum = 100;
        _active_threads_count = monthes;

        for(int i = 0; i < monthes; i++)
        {
            threads[i] = new Thread(new MontRate(i + 1));
            threads[i].start();
        }

        //try
        //{
        //    for(int i = 0; i < 12; i++)
        //        threads[i].join();
        //}
        //catch(InterruptedException ex) { System.err.println(ex.getMessage()); }
        //
        //System.out.println("-------------------\nTotal: " + _sum);
    }
    // + + + + + + + + + + + + + + + + + + + + + + +
}
