package step.learning.IOC;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import step.learning.IOC.Services.Hash.IHashService;
import step.learning.IOC.Services.Hash.Md5HashService;
import step.learning.IOC.Services.Hash.Sha1HashService;
import step.learning.IOC.Services.Random.IRandomServices;
import step.learning.IOC.Services.Random.RandomServiceV1;
import step.learning.IOC.Services.Random.RandomServiceV2;

public class ConfigModule extends AbstractModule
{
    private IRandomServices _random_services1;
    private IRandomServices _random_services2;

    @Provides
    @Named("RandomService1")
    private IRandomServices InjectRandomServices1()
    {
        if(_random_services1 == null)
        {
            _random_services1 = new RandomServiceV1();
            _random_services1.Seed("initial");
        }

        return _random_services1;
    }

    @Provides
    @Named("RandomService2")
    private IRandomServices InjectRandomServices2()
    {
        if(_random_services2 == null)
        {
            _random_services2 = new RandomServiceV2();
            _random_services2.Seed("initial");
        }

        return _random_services2;
    }

    @Override
    protected void configure()
    {
        //bind(IHashService.class).to(Md5HashService.class);  // <-- MD5.
        // bind(IHashService.class).to(Sha1HashService.class);  // <-- SHA-1.
        // bind(IHashService.class).to(Sha256HashService.class);  // <-- SHA-256.
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        bind(IHashService.class).annotatedWith(Names.named("Digest-hash")).to(Md5HashService.class);
        bind(IHashService.class).annotatedWith(Names.named("Signature-hash")).to(Sha1HashService.class);
    }
}