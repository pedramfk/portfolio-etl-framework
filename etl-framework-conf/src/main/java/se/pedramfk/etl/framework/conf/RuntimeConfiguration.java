package se.pedramfk.etl.framework.conf;

import java.io.InputStream;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.Builder;
import se.pedramfk.etl.framework.conf.DataConfiguration.BaseLayerConf;
import se.pedramfk.etl.framework.conf.DataConfiguration.EvolvedLayerConf;


public final class RuntimeConfiguration {

    private final SparkConfiguration sparkConf;
    private final DataConfiguration dataConf;

    private final SparkSession spark;

    public RuntimeConfiguration(SparkConfiguration sparkConf, DataConfiguration dataConf) throws ClassNotFoundException {
        
        this.sparkConf = sparkConf;
        this.dataConf = dataConf;
        
        this.spark = createSparkSession();

    }

    public RuntimeConfiguration(InputStream sparkConf, InputStream dataConf) throws ClassNotFoundException {
        this(SparkConfiguration.create(sparkConf), DataConfiguration.create(dataConf));
    }

    public RuntimeConfiguration() throws ClassNotFoundException {
        this(SparkConfiguration.create(), DataConfiguration.create());
    }

    private final SparkSession createSparkSession() throws ClassNotFoundException {

        Builder sparkBuilder = SparkSession
            .builder()
            .appName(sparkConf.getAppName())
            .master(sparkConf.getMaster())
            .config(createSparkConf());

        return sparkConf.enableHiveSupport() ? 
            sparkBuilder.enableHiveSupport().getOrCreate() : 
            sparkBuilder.getOrCreate();

    }

    private final SparkConf createSparkConf() throws ClassNotFoundException {

        if (sparkConf.registerKryoClasses()) {

            return new SparkConf()
                .setAll(sparkConf.getSettingsAsScalaIterable())
                .registerKryoClasses(sparkConf.getKryoClassesAsClassArray());
        
        } else {

            return new SparkConf()
                .setAll(sparkConf.getSettingsAsScalaIterable());
                
        }

    }

    public final SparkConfiguration getSparkConfiguration() {
        return sparkConf;
    }

    public final DataConfiguration getDataConfiguration() {
        return dataConf;
    }

    public final BaseLayerConf getBaseLayerConfiguration() {
        return dataConf.getBaseLayerConf();
    }

    public final EvolvedLayerConf getEvolvedLayerConfiguration() {
        return dataConf.getEvolvedLayerConf();
    }

    public final SparkSession getSparkSession() {
        return spark;
    }

    public final void closeSparkSession() {
        spark.close();
    }
    
}
