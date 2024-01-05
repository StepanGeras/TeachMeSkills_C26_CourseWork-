package util_information.encryption;

import java.util.Base64;

public class Encryption {

    public static String coding (String string){

        byte[] coderInformation = Base64.getEncoder().encode(string.getBytes());

        return new String(coderInformation);
    }

    public static String deCoding (String string){

        byte[] deCoderInformation = Base64.getDecoder().decode(string.getBytes());

        return new String(deCoderInformation);
    }

}
