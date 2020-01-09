package com.geekymv.mr.sample.mapjoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    private String fileName;

    private OrderBean order = new OrderBean();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fs = (FileSplit) context.getInputSplit();
        fileName = fs.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(" ");
        if(fileName.equals("order.txt")) {
            order.setOrderId(fields[0]);
            order.setProductId(fields[1]);
            order.setProductName("");
            order.setPrice(Double.parseDouble(fields[2]));
        }else {
            order.setOrderId("");
            order.setProductId(fields[0]);
            order.setProductName(fields[1]);
            order.setPrice(0);
        }
        context.write(order, NullWritable.get());
    }
}
