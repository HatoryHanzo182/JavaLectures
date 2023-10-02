package step.learning.IOC;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import step.learning.IOC.Services.Hash.IHashService;
import step.learning.IOC.Services.Random.IRandomServices;

public class IOC2
{
    private final IHashService _digest_hash_service;
    private final IHashService _signature_hash_service;
    private final IRandomServices _random_service1;
    private final IRandomServices _random_service2;

    @Inject @Named("Digest-hash")
    private IHashService _digest_hash_service2;

    @Inject
    public IOC2(@Named("Digest-hash") IHashService digest_hash_service, @Named("Signature-hash") IHashService signature_hash_service,
                @Named("RandomService1") IRandomServices random_service, @Named("RandomService2") IRandomServices random_service2)
    {
        _digest_hash_service = digest_hash_service;
        _signature_hash_service = signature_hash_service;
        _random_service1 = random_service;
        _random_service2 = random_service2;
    }

    public void Run()
    {
        System.out.println("IOC2 demo!");
        System.out.println("IHashService(Digest-hash) --> " + _digest_hash_service.Hash("IOC2 demo!"));
        System.out.println("IHashService(Signature-hash) --> " + _signature_hash_service.Hash("IOC2 demo!"));
        System.out.println("IHashService(Digest-hash / Digest-hash2) --> " + _digest_hash_service.hashCode() + " / " +
                _digest_hash_service2.hashCode());
        System.out.println("- - - - - - - - - - - - - - - - - - - - - ");
        System.out.println("IRandomServices(V1) --> " + _random_service1.RandomHex(6));
        System.out.println("IRandomServices(V2) --> " + _random_service2.RandomHex(6));
    }
}