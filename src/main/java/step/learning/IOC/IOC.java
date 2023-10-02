package step.learning.IOC;

import com.google.inject.Inject;
import step.learning.IOC.Services.IHashService;
import step.learning.IOC.Services.Md5HashService;
import step.learning.IOC.Services.Sha1HashService;
import step.learning.IOC.Services.Sha256HashService;

public class IOC
{
    @Inject
    private IHashService _hash_service;
    @Inject
    private Md5HashService _md5;
    @Inject
    private Sha1HashService _sha1;
    @Inject
    private Sha256HashService _sha256;

    public void Run()
    {
        System.out.println("Unencrypted string -> [IOC demo!]");
        System.out.println("IHashService -> [" + _hash_service.Hash("IOC DEMO!") + "]");
        System.out.println("Md5HashService -> [" + _md5.Hash("IOC DEMO!") + "]");
        System.out.println("Sha1HashService -> [" + _sha1.Hash("IOC DEMO!") + "]");
        System.out.println("Sha256HashService -> [" + _sha256.Hash("IOC DEMO!") + "]");
    }
}