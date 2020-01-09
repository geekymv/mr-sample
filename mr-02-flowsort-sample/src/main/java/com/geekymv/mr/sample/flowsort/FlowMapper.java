package com.geekymv.mr.sample.flowsort;

import com.geekymv.mr.sample.flowsort.bean.FlowBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by geekymv on 2018/3/31.
 */
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

    private FlowBean flowBean = new FlowBean();

    private Text phone = new Text();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fileds = line.split("\t");
        flowBean.setUpFlow(Long.parseLong(fileds[1]));
        flowBean.setDownFlow(Long.parseLong(fileds[2]));
        flowBean.setSumFlow(flowBean.getUpFlow() + flowBean.getDownFlow());

        phone.set(fileds[0]);

        context.write(flowBean, phone);
    }
}
