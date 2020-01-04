package com.geekymv.mr.sample.inputformat;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 自定义RecordReader
 * 每次处理一个小文件，把这个文件直接读成kv值，一次读完这个小文件
 */
public class WholeFileRecordReader extends RecordReader<Text, BytesWritable>{

    // 是否读取文件，初始是未读
    private boolean isRead = false;

    private Text key = new Text();

    private BytesWritable value = new BytesWritable();

    private FileSplit split;

    private FSDataInputStream fis;

    /**
     * 初始化方法，框架会在开始的时候调用一次
     * @param inputSplit
     * @param taskAttemptContext
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        split = (FileSplit)inputSplit;
        // 获取切片路径
        Path path = split.getPath();
        // 获取文件系统
        FileSystem fs = path.getFileSystem(taskAttemptContext.getConfiguration());
        fis = fs.open(path);
    }

    /**
     * 读取下一组kv值
     * @return 如果读取到了，返回true；否则返回false。
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(isRead) {
            // 已经读取过了
            return false;
        }
        // 还没有读，将读取状态设置成已读
        isRead = true;

        // 设置key为文件路径
        key.set(split.getPath().toString());

        System.out.println(split.getLength() + " ==== " + fis.available());

        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        // 设置value为文件内容
        value.set(buffer, 0, buffer.length);

        return true;
    }

    /**
     * 获取当前读到的key
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    /**
     * 获取当前读到的value
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    /**
     * 当前数据读取进度
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public float getProgress() throws IOException, InterruptedException {
        return isRead ? 1 : 0;
    }

    /**
     * 关闭资源
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if(fis != null) {
            fis.close();
        }
//        IOUtils.closeStream(fis); // 工具方法
    }
}
