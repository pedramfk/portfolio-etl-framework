package se.pedramfk.etl.framework.utils;

import scala.Tuple2;
import java.util.List;
import java.util.function.Function;
import static scala.collection.JavaConverters.iterableAsScalaIterable;
import static se.pedramfk.etl.framework.utils.ConfUtils.KeyValueSetting;


public final class StreamUtils {

    public static final scala.collection.Iterable<Tuple2<String, String>> getKeyValueSettingStreamAsScalaIterable(
        List<KeyValueSetting> settings) {

        return iterableAsScalaIterable(getKeyValueSettingStreamAsJavaIterable(settings));

    }

    public static final java.lang.Iterable<Tuple2<String, String>> getKeyValueSettingStreamAsJavaIterable(
        List<KeyValueSetting> settings) {

        return () -> settings.stream().map((Function<KeyValueSetting, Tuple2<String, String>>) s -> { 
            return new Tuple2<String, String> (s.getKey(), s.getValue()); 
        }).iterator();

    }

    public static final Class<?>[] getClassNamesAsClassArray(List<String> classNames) throws ClassNotFoundException {
        
        for (String className: classNames) {
            if (getClass(className) == null ) {
                throw new ClassNotFoundException(
                    String.format("could not get class for %s", className));
            }
        }

        return classNames
            .stream()
            .map(c -> { return getClass(c); })
            .toArray(Class<?>[]::new);
        
    }

    private static final Class<?> getClass(String className) {

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }

    }
    
}
