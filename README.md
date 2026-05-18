# LiteRT AI Demo

一个面向 AI 应用的 Android Compose 布局示例项目。

## 功能特性

### 界面结构

该应用包含四个主要区域：

1. **顶部栏 (TopAppBar)**
   - 显示应用标题 "LiteRT AI Demo"
   - 右侧包含更多选项按钮

2. **预览区 (Camera Preview)**
   - 灰色占位区域，显示相机图标和提示文字
   - 后续可替换为 CameraX 相机预览功能

3. **结果区 (Result Section)**
   - 显示模型名称 (Model)
   - 显示识别结果 (Result)
   - 显示置信度 (Confidence)
   - 显示推理时间 (Time)

4. **按钮区 (Button Section)**
   - 拍照识别：启动相机进行图像识别
   - 相册导入：从相册选择图片进行识别
   - 切换模型：在 MobileNet 和 ResNet 之间切换
   - 清空结果：清空所有识别结果

## 技术栈

- **语言**: Kotlin
- **框架**: Jetpack Compose
- **UI 组件**: Material3

## 项目结构

```
app/
├── src/
│   └── main/
│       ├── java/com/example/sy2_aicompose/
│       │   ├── MainActivity.kt          # 主活动
│       │   └── ui/theme/
│       │       ├── Color.kt             # 颜色定义
│       │       ├── Theme.kt             # 主题配置
│       │       └── Type.kt              # 文字样式
│       └── res/
│           └── values/
│               ├── colors.xml           # 资源颜色
│               └── strings.xml          # 字符串资源
```

## 自定义颜色

项目定义了以下自定义颜色：

| 颜色 | 值 | 用途 |
|------|-----|------|
| Blue600 | #1E88E5 | 拍照识别按钮 |
| Green600 | #43A047 | 相册导入按钮 |
| Purple600 | #7B1FA2 | 切换模型按钮 |
| Red600 | #E53935 | 清空结果按钮 |

## 运行项目

1. 在 Android Studio 中打开项目
2. 连接 Android 设备或创建模拟器
3. 点击运行按钮启动应用

## 开发说明

### Compose 组件

- 使用 `Column` 组织页面布局
- 使用 `Box` 作为预览区占位
- 使用 `Card + Column` 展示识别结果
- 使用 `Row/Column` 排列功能按钮

### 状态管理

使用 `remember` 和 `mutableStateOf` 管理 UI 状态：
- `modelName`: 当前模型名称
- `result`: 识别结果
- `confidence`: 置信度
- `inferenceTime`: 推理时间

## 后续扩展

可以进一步扩展的功能：
- 集成 CameraX 实现相机预览
- 接入 TensorFlow Lite 模型进行图像识别
- 添加图片选择功能
- 实现模型切换逻辑