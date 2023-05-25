package se.pedramfk.etl.framework.conf;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static se.pedramfk.etl.framework.utils.TestUtils.printConf;


@TestInstance(Lifecycle.PER_CLASS)
public final class TestDataConfiguration {

    private DataConfiguration dataConfiguration;

    @BeforeAll
    public void setup() {
        dataConfiguration = DataConfiguration.create(ClassLoader.getSystemResourceAsStream("data-conf.yaml"));
    }

    @Test
    public void testBaseLayerConf() {
        printConf("data.layer.base.path", dataConfiguration.getBaseLayerConf().getPath());
        printConf("data.layer.base.format", dataConfiguration.getBaseLayerConf().getFormat());
    }

    @Test
    public void testEvolvedLayerConf() {
        printConf("data.layer.evolved.path", dataConfiguration.getEvolvedLayerConf().getPath());
        printConf("data.layer.evolved.format", dataConfiguration.getEvolvedLayerConf().getFormat());
        printConf("data.layer.evolved.partitioning", dataConfiguration.getEvolvedLayerConf().getPartitioning().toString());
        printConf("data.layer.evolved.stream.format", dataConfiguration.getEvolvedLayerConf().getStreamConf().getFormat());
        printConf("data.layer.evolved.stream.outputMode", dataConfiguration.getEvolvedLayerConf().getStreamConf().getOutputMode());
        printConf("data.layer.evolved.stream.trigger", dataConfiguration.getEvolvedLayerConf().getStreamConf().getTrigger());
    }

}