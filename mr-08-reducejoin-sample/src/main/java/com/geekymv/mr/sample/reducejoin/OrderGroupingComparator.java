package com.geekymv.mr.sample.reducejoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 根据productId分组
 */
public class OrderGroupingComparator extends WritableComparator {

    public OrderGroupingComparator() {
        super(OrderBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean o1 = (OrderBean) a;
        OrderBean o2 = (OrderBean) b;
        return o1.getProductId().compareTo(o2.getProductId());
    }
}
