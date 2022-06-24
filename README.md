# R_UI项目结构
- app库是demo
- r_ui是组件库源码
#### 1. 通用RecyclerView.Adapter和Item
位置：r_ui的item包下(r_ui/src/main/java/com/dev/rexhuang/rui/item)
使用方法：
1. 使用RAdapter作为RecyclerView的adapter
2. 继承RDataItem提供自己的Item实例，其中泛型DATA为Item需要的数据类型，泛型VH为RecyclerView.ViewHolder子类
3. RDataItem#getItemLayoutRes以及#getItemView方法用于提供view的资源ID或view实例本身，RDataItem#onBindData方法用于绑定数据和ViewHolder中的View

**例子:app/src/main/java/com/dev/rexhuang/r_ui/app/recyclerview**