package se.pedramfk.etl.framework.commons.immutable;

import scala.Function1;
import org.apache.spark.sql.Dataset;

import se.pedramfk.etl.framework.commons.DataLayer;
import se.pedramfk.etl.framework.commons.DataType;


public interface ImmutableDataLayer<T extends DataType> extends DataLayer<T> {

    public Function1<Dataset<T>, Dataset<T>> filter();

}
