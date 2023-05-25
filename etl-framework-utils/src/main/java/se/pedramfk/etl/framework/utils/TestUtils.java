package se.pedramfk.etl.framework.utils;

public final class TestUtils {

    public static final void printConf(String key, String value) {
        System.out.println(String.format("%s=%s", key, value));
    }

    public static final void printConf(String key, boolean value) {
        System.out.println(String.format("%s=%s", key, Boolean.toString(value)));
    }
    
}
