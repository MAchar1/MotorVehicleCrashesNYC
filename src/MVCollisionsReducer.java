import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class MVCollisionsReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    @Override
    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output,
                       Reporter reporter) throws IOException {
        double totalCasualties = 0;
        while (values.hasNext()) {
            DoubleWritable value = values.next();
            System.out.println("value for " + key + " is " + value.get());
            totalCasualties += value.get();
        }

        output.collect(key, new DoubleWritable(totalCasualties));

    }
}
