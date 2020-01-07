package com.geekymv.mr.sample.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LogDriver {

    public static void main(String[] args) throws Exception {
        Job job = Job.getInstance(new Configuration(), "logDriver");
        job.setJarByClass(LogDriver.class);

        job.setOutputFormatClass(LogOutputformat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

}
