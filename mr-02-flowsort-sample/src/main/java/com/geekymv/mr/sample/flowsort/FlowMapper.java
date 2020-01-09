package com.geekymv.mr.sample.flowsort;

import com.geekymv.mr.sample.flowsort.bean.FlowBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by geekymv on 2018/3/31.
 */
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, NullWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fileds = line.split("\t");

        FlowBean flowBean = new FlowBean(fileds[0], Long.parseLong(fileds[1]), Long.parseLong(fileds[2]));

        context.write(flowBean, NullWritable.get());
    }
}
