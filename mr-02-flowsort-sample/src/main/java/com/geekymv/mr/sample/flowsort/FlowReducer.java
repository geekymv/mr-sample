package com.geekymv.mr.sample.flowsort;

import com.geekymv.mr.sample.flowsort.bean.FlowBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by geekymv on 2018/3/31.
 */
public class FlowReducer extends Reducer<FlowBean, NullWritable, FlowBean, NullWritable> {

    @Override
    protected void reduce(FlowBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key, NullWritable.get());
    }
}
