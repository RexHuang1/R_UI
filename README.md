# R_UI项目结构
- app库是demo
- r_ui是组件库源码



#### 1. 通用RecyclerView.Adapter和Item
主要用于解决不同样式的item可以直接添加到同一个recyclerview中的显示。
位置：r_ui的item包下(r_ui/src/main/java/com/dev/rexhuang/rui/item)
使用方法：
1. 使用RAdapter作为RecyclerView的adapter
2. 继承RDataItem提供自己的Item实例，其中泛型DATA为Item需要的数据类型，泛型VH为RecyclerView.ViewHolder子类
3. RDataItem#getItemLayoutRes以及#getItemView方法用于提供view的资源ID或view实例本身，RDataItem#onBindData方法用于绑定数据和ViewHolder中的View

**例子:app/src/main/java/com/dev/rexhuang/r_ui/app/recyclerview**





#### 2. 通用输入框封装InputItemLayout
主要用于登录,注册界面这种信息输入框这种繁琐单一的界面
位置: r_ui的input包下(r_ui/src/main/java/com/dev/rexhuang/rui/input/InputItemLayout.kt)
使用方法:
1. 在布局文件中直接引用
2. 属性说明
     ```
     属性hint:表示输入框提示文本
     属性title:表示输入框标题
     属性inputType:text|password|name,三种输入文本后的显示样式
     属性inputTextAppearance:style引用,表示输入框文本的样式
     	hintColor:输入框提示文本的文字颜色
         inputColor:输入框输入文本的文字颜色
         textSize:输入框文本的文字大小
         maxInputLength:输入框输入文本的最大长度
     属性titleTextAppearance:style引用,表示输入框标题的样式
     	titleColor:输入框标题的文字颜色
         titleSize:输入框标题的文字大小
         minWidth:输入框标题的最小长度
     属性topLineAppearance:style引用,表示输入框的上分割线的样式
     	color:分割线颜色
         height:分割线宽度
         leftMargin:分割线左边距
         rightMargin:分割线右边距
         enable:是否有分割线,若为false则不显示
     属性bottomLineAppearance:style引用,表示输入框的下分割线的样式
     	同topLineAppearance
     ```
3. 在styles.xml定义好需要的style引用,直接赋值于布局文件的属性中

**例子:app/src/main/java/com/dev/rexhuang/r_ui/app/input/LoginActivity.kt**