package com.geekymv.mr.sample.flowsort;

import com.geekymv.mr.sample.flowsort.bean.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by geekymv on 2018/3/31.
 */
public class FlowReducer extends Reducer<FlowBean, Text, Text, FlowBean> {

    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for(Text phone : values) {
            context.write(phone, key);
        }
    }
}
