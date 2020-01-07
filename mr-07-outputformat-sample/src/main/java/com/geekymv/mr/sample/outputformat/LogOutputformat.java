package com.geekymv.mr.sample.outputformat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LogOutputformat extends FileOutputFormat<LongWritable, Text> {

    @Override
    public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        LogRecordWriter recordWriter = new LogRecordWriter();
        recordWriter.initialize(context);
        return recordWriter;
    }
}
