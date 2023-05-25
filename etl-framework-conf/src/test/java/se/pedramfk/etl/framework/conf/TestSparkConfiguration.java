package se.pedramfk.etl.framework.conf;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static se.pedramfk.etl.framework.utils.ConfUtils.KeyValueSetting;
import static se.pedramfk.etl.framework.utils.TestUtils.printConf;


@TestInstance(Lifecycle.PER_CLASS)
public final class TestSparkConfiguration {

    private SparkConfiguration sparkConfiguration;

    @BeforeAll
    public void setup() {
        sparkConfiguration = SparkConfiguration.create(ClassLoader.getSystemResourceAsStream("spark-conf.yaml"));
    }

    @Test
    public void testAppName() {
        printConf("spark.appName", sparkConfiguration.getAppName());
    }

    @Test
    public void testMaster() {
        printConf("spark.master", sparkConfiguration.getMaster());
    }

    @Test
    public void testEnableHiveSupport() {
        printConf("spark.enableHiveSupport", sparkConfiguration.enableHiveSupport());
    }

    @Test
    public void testRegisterKryoClasses() {
        printConf("spark.registerKryoClasses", sparkConfiguration.registerKryoClasses());
    }

    @Test
    public void testSettings() {
        for (KeyValueSetting setting: sparkConfiguration.getSettings()) {
            printConf(String.format("spark.%s", setting.getKey()), setting.getValue());
        }
    }

    @Test
    public void testKryoClasses() {
        printConf("spark.kryoClasses", sparkConfiguration.getKryoClasses().toString());
    }

}
