package se.pedramfk.etl.framework.commons.immutable.fs;

import java.sql.Timestamp;

import static org.apache.spark.sql.types.DataTypes.LongType;
import static org.apache.spark.sql.types.DataTypes.IntegerType;
import static org.apache.spark.sql.types.DataTypes.StringType;
import static org.apache.spark.sql.types.DataTypes.BooleanType;
import static org.apache.spark.sql.types.DataTypes.TimestampType;
import static org.apache.spark.sql.types.DataTypes.createStructField;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import lombok.Getter;
import lombok.Setter;
import se.pedramfk.etl.framework.commons.DataType;


public final class TestImmutableFsLayer {

    
    
    public static final class SampleData implements DataType {

        private static final long serialVersionUID = 100L;

        @Getter @Setter private long id;
        @Getter @Setter private boolean active;
        @Getter @Setter private String name;
        @Getter @Setter private int age;
        @Getter @Setter private Timestamp timestamp;

        public static final StructType SCHEMA = new StructType(new StructField[] {
            createStructField("id", LongType, false), 
            createStructField("active", BooleanType, false), 
            createStructField("name", StringType, false), 
            createStructField("age", IntegerType, false), 
            createStructField("timestamp", TimestampType, false), 
        });

    }
    
}
