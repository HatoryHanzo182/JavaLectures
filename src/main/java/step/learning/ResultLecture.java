package step.learning;

import com.google.inject.Guice;
import step.learning.IOC.ConfigModule;
import step.learning.IOC.IOC;
import com.google.inject.Injector;

public class ResultLecture
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new ConfigModule());
        IOC ioc = injector.getInstance(IOC.class);

        ioc.Run();
    }
}