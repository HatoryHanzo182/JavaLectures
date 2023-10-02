package step.learning.IOC;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import step.learning.IOC.Services.IHashService;

public class ICO2
{
    private final IHashService _digest_hash_service;
    private final IHashService _signature_hash_service;

    @Inject
    public ICO2(@Named("Digest-hash") IHashService digest_hash_service, @Named("Digest-hash") IHashService signature_hash_service)
    {
        _digest_hash_service = digest_hash_service;
        _signature_hash_service = signature_hash_service;
    }

    public void Run()
    {
        System.out.println("IOC2 DEMO");
        System.out.println(_digest_hash_service.Hash("IOC2 DEMO"));
        System.out.println(_signature_hash_service.Hash("IOC2 DEMO"));
    }
}
