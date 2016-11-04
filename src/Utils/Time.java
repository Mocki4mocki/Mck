package Utils;

/**
 * Created by Mocki on 02.11.2016.
 */

public class Time {

    public static final long    SECOND = 1000000000l;

    public static long getSecond() {
        return System.nanoTime();
    }

}
