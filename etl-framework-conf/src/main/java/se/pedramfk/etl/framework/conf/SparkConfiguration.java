package se.pedramfk.etl.framework.conf;

import scala.Tuple2;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;
import lombok.Getter;
import lombok.Setter;

import static se.pedramfk.etl.framework.utils.ConfUtils.KeyValueSetting;
import static se.pedramfk.etl.framework.utils.StreamUtils.getClassNamesAsClassArray;
import static se.pedramfk.etl.framework.utils.StreamUtils.getKeyValueSettingStreamAsScalaIterable;


public final class SparkConfiguration {

    private static final InputStream DEFAULT_CONF_FILE = ClassLoader.getSystemResourceAsStream("spark-conf.yaml");
    
    @Getter @Setter private String appName = null;
    @Getter @Setter private String master = "local[*]";

    private boolean enableHiveSupport = false;
    private boolean registerKryoClasses = false;

    @Getter @Setter private List<KeyValueSetting> settings = new ArrayList<KeyValueSetting>() ;
    @Getter @Setter private List<String> kryoClasses = new ArrayList<String>();

    public boolean enableHiveSupport() { return enableHiveSupport; }
    public boolean registerKryoClasses() { return registerKryoClasses; }

    public void setEnableHiveSupport(boolean enableHiveSupport) { this.enableHiveSupport = enableHiveSupport; }
    public void setRegisterKryoClasses(boolean registerKryoClasses) { this.registerKryoClasses = registerKryoClasses; }

    public static final SparkConfiguration create(InputStream conf) {
        return new Yaml().loadAs(conf, SparkConfiguration.class);
    }

    public static final SparkConfiguration create() {
        return new Yaml().loadAs(DEFAULT_CONF_FILE, SparkConfiguration.class);
    }

    public final scala.collection.Iterable<Tuple2<String, String>> getSettingsAsScalaIterable() {
        return getKeyValueSettingStreamAsScalaIterable(settings);
    }

    public final Class<?>[] getKryoClassesAsClassArray() throws ClassNotFoundException {
        return getClassNamesAsClassArray(kryoClasses);
    }

}
