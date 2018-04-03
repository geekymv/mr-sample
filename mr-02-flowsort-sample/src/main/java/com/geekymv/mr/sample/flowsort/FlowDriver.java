package com.geekymv.mr.sample.flowsort;

import com.geekymv.mr.sample.flowsort.bean.FlowBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by geekymv on 2018/3/31.
 * 需求
 * 1.
 */
public class FlowDriver {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "FlowDriver");

        job.setJarByClass(FlowDriver.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path("/usr/root/flow/input/flow.data"));

        FileSystem fs = FileSystem.get(conf);
        Path out = new Path("/usr/root/flow/output");
        if(fs.exists(out)) {
            fs.delete(out, true);
        }

        FileOutputFormat.setOutputPath(job, out);

        job.waitForCompletion(true);
    }
}