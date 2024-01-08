package util_information.encryption;

import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;

public class Encryption {

    public static String coding (String string){

        String coderInformation = Base64.getEncoder().encodeToString(string.getBytes());

        return sold(coderInformation);
    }

    public static String deCoding (String string){

        String startTrimSalt = string.substring(10, string.length() - 10);

        byte[] deCoderInformation = Base64.getDecoder().decode(startTrimSalt);

        return new String(deCoderInformation);
    }

    private static String sold (String in) {

        String symbols = "qwertyuiop[]asdfghjkl;'zxcvbnm,./1234567890-=";

        String salt = new Random().ints (10, 0, symbols. length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        return salt + in + salt;

    }

}
