package com.geekymv.mr.sample.reducejoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class OrderReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {

    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        // 获取productName
        Iterator<NullWritable> iter = values.iterator();
        iter.next();
        String productName = key.getProductName();

        // 后面是没有productName的order信息
        while (iter.hasNext()) {
            iter.next();
            key.setProductName(productName);
            context.write(key, NullWritable.get());
        }

    }
}
