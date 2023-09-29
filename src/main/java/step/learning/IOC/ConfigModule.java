package step.learning.IOC;

import com.google.inject.AbstractModule;
import step.learning.IOC.Services.IHashService;
import step.learning.IOC.Services.Md5HashService;

public class ConfigModule extends AbstractModule
{
    @Override
    protected void configure() { bind(IHashService.class).to(Md5HashService.class); }
}