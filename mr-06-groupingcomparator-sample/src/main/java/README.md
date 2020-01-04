GroupingComparator 分组（辅助排序）
对Reduce 阶段的数据根据某一个或几个字段进行分组

分组排序步骤
- 自定义类继承 WritableComparator
- 重写compare方法
- 创建一个构造器将比较对象的类传给父类
