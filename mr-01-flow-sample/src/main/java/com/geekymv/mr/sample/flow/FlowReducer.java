package com.geekymv.mr.sample.flow;

import com.geekymv.mr.sample.flow.bean.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by geekymv on 2018/3/31.
 */
public class FlowReducer extends Reducer<Text, FlowBean, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        int sumUpFlow = 0;
        int sumDownFlow = 0;
        for(Iterator<FlowBean> iterator = values.iterator(); iterator.hasNext();) {
            FlowBean flowBean = iterator.next();
            sumUpFlow += flowBean.getUpFlow();
            sumDownFlow += flowBean.getDownFlow();
        }

        context.write(key, new Text(sumUpFlow + "\t" + sumDownFlow + "\t" + (sumUpFlow + sumDownFlow)));
    }
}
