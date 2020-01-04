https://blog.csdn.net/Coder__CS/article/details/79452486

Partition 分区
决定数据去往哪个ReduceTask

默认分区 HashPartitioner

mapper中的 context.write() 跟踪代码