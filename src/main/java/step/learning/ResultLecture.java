package step.learning;

import com.google.inject.Guice;
import step.learning.IOC.ConfigModule;
import step.learning.IOC.IOC;
import com.google.inject.Injector;
import step.learning.IOC.IOC2;

public class ResultLecture
{
    public static void main( String[] args )
    {
        Guice.createInjector(new ConfigModule()).getInstance(IOC2.class).Run();
    }
}