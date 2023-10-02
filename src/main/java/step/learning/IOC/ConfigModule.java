package step.learning.IOC;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import step.learning.IOC.Services.IHashService;
import step.learning.IOC.Services.Md5HashService;
import step.learning.IOC.Services.Sha1HashService;

public class ConfigModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        // Demo IOC -> bind(IHashService.class).to(Md5HashService.class);
        
        bind(IHashService.class).annotatedWith(Names.named("Digest-hash")).to(Md5HashService.class);
        bind(IHashService.class).annotatedWith(Names.named("Signature-hash")).to(Sha1HashService.class);
    }
}