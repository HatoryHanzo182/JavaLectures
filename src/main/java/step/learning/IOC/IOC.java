package step.learning.IOC;

import com.google.inject.Inject;
import step.learning.IOC.Services.IHashService;
import step.learning.IOC.Services.Md5HashService;

public class IOC
{
    @Inject
    private IHashService _hash_service;
    @Inject
    private Md5HashService _md5;

    public void Run()
    {
        System.out.println("IOC demo!");
        System.out.println(_hash_service.Hash("IOC DEMO!"));
        System.out.println(_md5.Hash("IOC DEMO!"));
    }
}