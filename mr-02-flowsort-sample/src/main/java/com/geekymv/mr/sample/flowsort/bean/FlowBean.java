package com.geekymv.mr.sample.flowsort.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by geekymv on 2018/3/31.
 * 排序 Comparable
 * MapReduce 默认根据key 排序
 *
 */
public class FlowBean implements WritableComparable<FlowBean> {

    /**
     * 上行流量
     */
    private long upFlow;

    /**
     * 下行流量
     */
    private long downFlow;

    /**
     * 总流量
     */
    private long sumFlow;

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    public FlowBean() {
    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }

    /**
     * 根据总流量倒序
     * 分组与排序相关，总流量相同的数据会进入同一个reduce方法
     * @param o
     * @return
     */
    @Override
    public int compareTo(FlowBean o) {
        return Long.compare(o.getSumFlow(), this.getSumFlow());
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }
}
