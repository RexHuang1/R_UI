# R_UI项目结构
- app库是demo
- r_ui是组件库源码



## 1. 通用RecyclerView.Adapter和Item
主要用于解决不同样式的item可以直接添加到同一个recyclerview中的显示。
位置：r_ui的item包下(r_ui/src/main/java/com/dev/rexhuang/rui/item)
使用方法：
1. 使用RAdapter作为RecyclerView的adapter
2. 继承RDataItem提供自己的Item实例，其中泛型DATA为Item需要的数据类型，泛型VH为RecyclerView.ViewHolder子类
3. RDataItem#getItemLayoutRes以及#getItemView方法用于提供view的资源ID或view实例本身，RDataItem#onBindData方法用于绑定数据和ViewHolder中的View

**例子:app/src/main/java/com/dev/rexhuang/r_ui/app/recyclerview**





## 2. 通用输入框封装InputItemLayout
主要用于登录,注册界面这种信息输入框这种繁琐单一的界面
位置: r_ui的input包下(r_ui/src/main/java/com/dev/rexhuang/rui/input/InputItemLayout.kt)
使用方法:
1. 在布局文件中直接引用
2. 属性说明
     ```
     属性hint:表示输入框提示文本
     属性title:表示输入框标题
     属性inputType:text|password|number,三种输入文本后的显示样式
     属性inputTextAppearance:style引用,表示输入框文本的样式
        hintColor:输入框提示文本的文字颜色
        inputColor:输入框输入文本的文字颜色
        textSize:输入框文本的文字大小
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
**例子:app/src/main/java/com/dev/rexhuang/r_ui/app/input/RegisterActivity.kt**




## 3.通用分类View:RSliderView
主要用于分类的界面搭建

## 4.通用导航栏RNavigationBar
主要用于导航栏的快速开发
位置: r_ui的nav包下(r_ui/src/main/java/com/dev/rexhuang/rui/nav/RNavigationBar.kt)
使用方法:
1. 在布局文件中直接引用
2. 属性说明
     ```
     属性text_btn_text_size:表示输入框提示文本
     属性text_btn_text_color:表示输入框标题
     属性title_text_size:表示主标题的文字大小
     属性title_text_size_with_subTitle:表示有主副标题时主标题的文字大小
     属性title_text_color:表示主标题的文字颜色
     属性subTitle_text_size:表示副标题的文字大小
     属性subTitle_text_color:表示副标题的文字颜色
     属性nav_icon:表示导航栏的导航图标(即在导航栏左侧一般为返回图标,以iconfont字体文字来表示)
     属性nav_icon_size:表示导航栏的导航图标(即在导航栏左侧一般为返回图标,以iconfont字体文字来表示)的大小
     属性nav_icon_color:表示导航栏的导航图标(即在导航栏左侧一般为返回图标,以iconfont字体文字来表示)的颜色
     属性nav_title:表示导航栏主标题文本
     属性nav_subtitle:表示导航栏副标题文本
     属性hor_padding:表示按钮的横向内间距
     属性nav_line_color:表示导航栏下划线颜色
     属性nav_line_color:表示导航栏下划线颜色
     属性nav_line_height:表示导航栏下划线的宽度
     ```
3. 可以在布局文件中直接设置需要改变的属性(有默认style:navigationStyle,设置的属性会优先于默认style中的属性)
4. 也可以在styles.xml(theme中设置RNavigationStyle属性,但需要现在attrs中定义<attr name="RNavigationStyle" format="reference" />)定义好需要的style引用
5. 优先级从高到低位(xml-----theme------默认style:navigationStyle)

**例子:app/src/main/java/com/dev/rexhuang/r_ui/app/input/LoginActivity.kt**