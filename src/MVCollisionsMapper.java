import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class MVCollisionsMapper extends MapReduceBase implements Mapper<LongWritable,
        Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException {
        String row = value.toString();
        String[] data = row.split(",");
        String streetName = "N/A";
        int injured = 0;
        int deaths = 0;
        if (!data[8].isEmpty()) {
            streetName = data[8];
            System.out.println("ON STREET: " + streetName);
            // Here we add the three categories of injured people
            // (pedestrians, cyclists, and motorists)
            // to get total injured
            System.out.println("Ped Injured: " + data[12]);
            injured = Integer.parseInt(data[12].trim()) + Integer.parseInt(data[14]) + Integer.parseInt(data[16]);
            // Here we do the same as above, with deaths
            deaths = Integer.parseInt(data[13]) + Integer.parseInt(data[15]) + Integer.parseInt(data[17]);
        } else if (!data[9].isEmpty()) {
            streetName = data[9];
            System.out.println("OFF STREET: " + streetName);
            // Same as above but this is to catch when there is no ON STREET GIVEN
            System.out.println(data[12]);
            injured = Integer.parseInt(data[12]) + Integer.parseInt(data[14]) + Integer.parseInt(data[16]);
            deaths = Integer.parseInt(data[13]) + Integer.parseInt(data[15]) + Integer.parseInt(data[17]);
        }

        System.out.println("streetName: " + streetName);
        int casualties = injured + deaths;
        System.out.println("Casualties: " + casualties);

        output.collect(new Text(streetName), new IntWritable(casualties));
        System.out.println("done");

    }

}
