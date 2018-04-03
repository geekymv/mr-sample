package com.geekymv.mr.sample.flow;

import com.geekymv.mr.sample.flow.bean.FlowBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by geekymv on 2018/3/31.
 * 需求
 * 1.统计每一个用户（手机号）所耗费的总上行流浪、总下行流量、总流量
 */
public class FlowDriver {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "FlowDriver");

        job.setJarByClass(FlowDriver.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path("/usr/root/flow/input/flow.data"));
        FileOutputFormat.setOutputPath(job, new Path("/usr/root/flow/output"));

        job.waitForCompletion(true);
    }
}
