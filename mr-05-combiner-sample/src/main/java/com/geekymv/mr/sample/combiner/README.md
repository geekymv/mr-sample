Combiner 合并
- Combiner 是MR程序中Mapper和Reducer之外的一种组件
- Combiner 组件的父类就是Reducer
- Combiner 和 Reducer 的区别就在于运行的位置
Combiner是在每一个MapTask所在的节点运行，Reducer 是接收全局所有Mapper的输出结果。
- Combiner 的意义就是对每一个MapTask的输出进行局部汇总，以减少网络传输量。
- Combiner 能够应用的前提是不能影响最终的业务逻辑，而且Combiner 的输出kv 应该跟Reducer 的输入kv类型对应起来
也就是说Combiner 的输入输出类型要一致。

自定义Combiner 的步骤
- 自定义一个Combiner 继承Reducer，重写reduce方法
- 