package wayc.backend.utils;

import java.util.Random;

public class RandomUtils {

    private RandomUtils() {}

    public static String createAuthKey(){
        Random random = new Random();
        return String.valueOf(random.nextInt(888888) + 111111);
    }
}
