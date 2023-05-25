package se.pedramfk.etl.framework.commons;

import org.apache.spark.sql.Dataset;

import se.pedramfk.etl.framework.conf.RuntimeConfiguration;


/**
 * <p>Representation of data-layer of arbitrary schema.</p>
 * 
 * <p>
 * This contains statically defined operations that are performed on data for this layer, 
 * which can be configurable through runtime-configurations {@link #getConfiguration()}.
 * </p>
 * 
 * @param <T>   statically defined schema/encoding for data
 * @version 0.0.1-SNAPSHOT
 * @author Pedram Fathollahzadeh, pedramkf@kth.se
 */
public interface DataLayer<T extends DataType> {

    public Dataset<T> getData();

    public Dataset<T> getDataStream();

    public RuntimeConfiguration getConfiguration();

}
