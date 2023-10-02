package step.learning.IOC.Services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashService implements IHashService
{
    private final char[] _HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    @Override
    public String Hash(String input)
    {
        if (input == null)
            input = "";

        try
        {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            char[] chars = new char[32];
            short i = 0;

            for(int b : digest.digest(input.getBytes(StandardCharsets.UTF_8)))
            {
                String hex = String.format("%02x", b & 0xFF);

                chars[i] = hex.charAt(0);
                chars[i + 1] = hex.charAt(1);
                i += 2;
            }

            return new String(chars);
        }
        catch (NoSuchAlgorithmException e) { throw new RuntimeException(e); }
    }
}