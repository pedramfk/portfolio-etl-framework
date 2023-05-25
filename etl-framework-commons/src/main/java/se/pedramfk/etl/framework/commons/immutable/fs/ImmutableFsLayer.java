package se.pedramfk.etl.framework.commons.immutable.fs;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.types.StructType;

import se.pedramfk.etl.framework.commons.DataType;
import se.pedramfk.etl.framework.commons.immutable.ImmutableDataLayer;
import se.pedramfk.etl.framework.conf.RuntimeConfiguration;


public abstract class ImmutableFsLayer<T extends DataType> implements ImmutableDataLayer<T> {

    private final RuntimeConfiguration conf;
    private final Encoder<T> encoder;
    private final StructType schema;

    public ImmutableFsLayer(RuntimeConfiguration conf, Encoder<T> encoder, StructType schema) {
        this.conf = conf;
        this.encoder = encoder;
        this.schema = schema;
    }

    @Override
    public Dataset<T> getData() {

        return conf.getSparkSession()
            .read()
            .format(conf.getBaseLayerConfiguration().getFormat())
            .option("path", conf.getBaseLayerConfiguration().getPath())
            .schema(schema)
            .load()
            .as(encoder);

    }

    @Override
    public Dataset<T> getDataStream() {

        return conf.getSparkSession()
            .readStream()
            .format(conf.getBaseLayerConfiguration().getFormat())
            .option("path", conf.getBaseLayerConfiguration().getPath())
            .schema(schema)
            .load()
            .as(encoder);
            
    }

    @Override
    public RuntimeConfiguration getConfiguration() {
        return conf;
    }
    
}
