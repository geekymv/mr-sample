package com.geekymv.mr.sample.group;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {

    // 订单ID
    private String orderId;

    // 商品ID
    private String productId;

    // 商品价格
    private Double price;

    /**
     * 先根据订单ID排序，订单ID相同的按照价格降序排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(OrderBean o) {
        int compare = this.getOrderId().compareTo(o.getOrderId());
        if(compare == 0) {
            return Double.compare(o.getPrice(), this.getPrice());
        }
        return compare;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeUTF(productId);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readUTF();
        this.productId = in.readUTF();
        this.price = in.readDouble();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return orderId + "\t" + productId + "\t" + price;
    }
}
