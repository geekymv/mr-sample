package com.geekymv.mr.sample.patition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by geekymv on 2018/3/31.
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fileds = line.split(" ");

        FlowBean flowBean = new FlowBean(Long.parseLong(fileds[1]), Long.parseLong(fileds[2]));

        context.write(new Text(fileds[0]), flowBean);
    }
}
