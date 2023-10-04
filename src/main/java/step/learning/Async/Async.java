package step.learning.Async;

public class Async
{
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

    public void Run()
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
}
