package se.pedramfk.etl.framework.conf;

import lombok.Getter;
import lombok.Setter;
import java.io.InputStream;
import java.util.ArrayList;
import org.yaml.snakeyaml.Yaml;


public final class DataConfiguration {

    private static final InputStream DEFAULT_CONF_FILE = ClassLoader.getSystemResourceAsStream("data-conf.yaml");

    @Getter @Setter private BaseLayerConf baseLayerConf;
    @Getter @Setter private EvolvedLayerConf evolvedLayerConf;

    public static final DataConfiguration create(InputStream conf) {
        return new Yaml().loadAs(conf, DataConfiguration.class);
    }

    public static final DataConfiguration create() {
        return new Yaml().loadAs(DEFAULT_CONF_FILE, DataConfiguration.class);
    }

    private static class LayerConf {
        @Getter @Setter private String path;
        @Getter @Setter private String format;        
    }

    public static final class BaseLayerConf extends LayerConf {
    }
    
    public static final class EvolvedLayerConf extends LayerConf {
        @Getter @Setter private ArrayList<String> partitioning;
        @Getter @Setter private StreamConf streamConf;
    }
    
    public static final class StreamConf {
        @Getter @Setter private String format;
        @Getter @Setter private String outputMode;
        @Getter @Setter private String trigger;
    }
    
}


