需求
将多个小文件合并成一个SequenceFile文件（SequenceFile文件是Hadoop用来存储二进制形式的key-value对的文件格式），
SequenceFile里面存储着多个文件，存储的形式为文件路径+名称为key，文件内容为value。

输入数据
one.txt two.txt three.txt

期望输出文件格式
part-r-0000


Input -> InputFormat -> Mapper -> Shuffle -> Reducer -> OutputFormat -> Output
