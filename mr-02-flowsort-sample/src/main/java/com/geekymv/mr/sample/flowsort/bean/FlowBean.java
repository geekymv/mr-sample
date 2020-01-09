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

    private String phone;

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
        return phone + "\t" + upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    public FlowBean() {
    }

    public FlowBean(String phone, long upFlow, long downFlow) {
        this.phone = phone;
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(phone);
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
        this.phone = in.readUTF();
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }

    /**
     * 根据总流量倒序
     * @param o
     * @return
     */
    @Override
    public int compareTo(FlowBean o) {
        int compare = Long.compare(o.getSumFlow(), this.getSumFlow());
        if(compare == 0) {
            return this.phone.compareTo(o.getPhone());
        }
        return compare;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
