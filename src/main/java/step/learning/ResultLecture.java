package step.learning;

import com.google.inject.Guice;
import step.learning.Async.Async;
import step.learning.IOC.ConfigModule;
import com.google.inject.Injector;
import step.learning.IOC.IOC;
import step.learning.IOC.IOC2;

public class ResultLecture
{
    public static void main( String[] args )
    {
        Async async = new Async();

        async.Run();
    }
}