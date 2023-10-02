package step.learning.IOC;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import step.learning.IOC.Services.*;

public class IOC
{
    @Inject
    private IHashService _hash_service;
    @Inject
    private Md5HashService _md5;
    @Inject
    private Md5OldHashService _old_md5;
    @Inject
    private Sha1HashService _sha1;
    @Inject
    private Sha256HashService _sha256;

    public void Run()
    {
        long t1;
        long t2;
        String hash;
        String hash2;

        System.out.println("Unencrypted string -> [IOC demo!]");
        System.out.println("IHashService -> [" + _hash_service.Hash("IOC DEMO!") + "]");

        t1 = System.nanoTime();
        hash =  "Md5HashService -> [" + _md5.Hash("IOC DEMO!") + "]";
        t2 = System.nanoTime();

        System.out.println(hash + " \t:" + (t2-t1) + "ms");

        t1 = System.nanoTime();
        hash2 = "Md5OldHashService -> [" + _old_md5.Hash("IOC DEMO!") + "]";
        t2 = System.nanoTime();

        System.out.println(hash2 + " \t:" + (t2-t1) + "ms");
        System.out.println("Md5HashService -> [" + _md5.Hash("IOC DEMO!") + "]");
        System.out.println("Sha1HashService -> [" + _sha1.Hash("IOC DEMO!") + "]");
        System.out.println("Sha256HashService -> [" + _sha256.Hash("IOC DEMO!") + "]");
    }
}

//  public static void main( String[] args )
//  {
//      Injector injector = Guice.createInjector(new ConfigModule());
//      IOC ioc = injector.getInstance(IOC.class);
//
//      ioc.Run();
//  }


