package step.learning.IOC;

import com.google.inject.Inject;
import step.learning.IOC.Services.IHashService;

public class IOC
{
    @Inject
    private IHashService _hash_service;

    public void Run()
    {
        System.out.println("IOC demo!");
        System.out.println(_hash_service.Hash("IOC DEMO!"));
    }
}