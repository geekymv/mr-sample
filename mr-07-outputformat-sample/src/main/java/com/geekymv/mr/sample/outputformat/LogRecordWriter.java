package com.geekymv.mr.sample.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LogRecordWriter extends RecordWriter<LongWritable, Text> {

    private FSDataOutputStream one;

    private FSDataOutputStream two;

    public void initialize(TaskAttemptContext context) throws IOException {
        Configuration configuration = context.getConfiguration();
        // 获取在LogDriver中设置的文件输出路径
        String outPath = configuration.get(FileOutputFormat.OUTDIR);
        // 获取文件系统
        FileSystem fs = FileSystem.get(configuration);

        one = fs.create(new Path(outPath + "/one.log"));
        two = fs.create(new Path(outPath + "/two.log"));
    }

    @Override
    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        String v = value.toString() + "\n";
        if(v.contains("baidu.com")) {
            one.write(v.getBytes());
        }else {
            two.write(v.getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(one);
        IOUtils.closeStream(two);
    }
}
