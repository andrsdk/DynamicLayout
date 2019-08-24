# DynamicLayout

> 动态布局


### 支持格式列表

| 名称  | 类型 | 说明 |
| ------------ | ------------ | ------------ |
| View | view | 背景, 线条 |
| TextView | text | 文本 |

### 支持的基本类型

* Size

> 描述属性的大小，包括宽度、高度、间距、字体大小等
> 可能值match、wrap、20dp、20px(默认)

### view支持的属性

| 字段  | 版本 | 说明 |
| ------------ | ------------ | ------------ |
| width | 1.0.0 | 布局的宽度, `Size`类型 |
| height| 1.0.0 | 布局的高度, `Size`类型 |
| paddingTop |1.0.0 | 布局的内边距, `Size`类型 |
| paddingBottom |1.0.0 | 布局的内边距, `Size`类型 |
| paddingLeft |1.0.0 | 布局的内边距, `Size`类型 |
| paddingRight |1.0.0 | 布局的内边距, `Size`类型 |
| background |1.0.0 | 布局的背景, #00ff00、red |

### text支持的属性
| 字段  | 版本 | 说明 |
| ------------ | ------------ | ------------ |
| ---- |  ---- | view所有支持的属性, text都支持 |
| text | 1.0.0 | text的内容 |
| fontColor |1.0.0 | text的文字的颜色, #00ff00、red |
| fontSize |1.0.0 | text的文字的大小, `Size`类型 |

