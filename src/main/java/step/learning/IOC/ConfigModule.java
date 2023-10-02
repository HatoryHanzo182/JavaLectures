package step.learning.IOC;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import step.learning.IOC.Services.IHashService;
import step.learning.IOC.Services.Md5HashService;
import step.learning.IOC.Services.Sha1HashService;
import step.learning.IOC.Services.Sha256HashService;

public class ConfigModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        // bind(IHashService.class).to(Md5HashService.class);  // <-- MD5.
        // bind(IHashService.class).to(Sha1HashService.class);  // <-- SHA-1.
        bind(IHashService.class).to(Sha256HashService.class);  // <-- SHA-256.
    }
}