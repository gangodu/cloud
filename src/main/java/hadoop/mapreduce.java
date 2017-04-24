//package hadoop;
//
///**
// * Created by praveen on 11/8/16.
// */
//
//import au.com.bytecode.opencsv.CSVReader;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.*;
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.Iterator;
//
//public class mapreduce {
//    public static void main(String[] args) throws IOException {
//        JobConf conf = new JobConf(mapreduce.class);
//        conf.setJobName("Wage Report");
//
//        conf.setOutputKeyClass(Text.class);
//        conf.setOutputValueClass(Text.class);
//        conf.setMapOutputKeyClass(Text.class);
//        conf.setMapOutputValueClass(Text.class);
//
//        conf.setMapperClass(Map.class);
//        conf.setNumMapTasks(2);
//        conf.setNumReduceTasks(1);
//        conf.setCombinerClass(Reduce.class);
//        conf.setReducerClass(Reduce.class);
//
//        FileInputFormat.setInputPaths(conf, new Path(args[0]));
//        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
//        JobClient.runJob(conf);
//    }
//
//    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
//        private Text city = new Text();
//        private LongWritable totalwage = new LongWritable();
//
//        public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
//            String record = value.toString();
//            CSVReader R = new CSVReader(new StringReader(record));
//
//            String[] mappedrecord = R.readNext();
//            R.close();
//            totalwage.set(mappedrecord[17]);
//            city.set(mappedrecord[4]);
//
//            output.collect(city, totalwage);
//        }
//    }
//
//    public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
//        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
//            int sum = 0;
//            while (values.hasNext()) {
//                sum += values.next().get();
//            }
//            output.collect(key, new IntWritable(sum));
//        }
//    }
//}
