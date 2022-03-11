# jetpackDemo
jetpack

## Jetpack Component
| library | version | description |
| ------- | ------- | ----------- |
| [startup][startup] | 1.1.1   | 可在应用启动时简单、高效地初始化组件 |
| [lifecycle][lifecycle] | 2.5.0-alpha02 | 生命周期感知型组件可执行操作来响应另一个组件（如 Activity 和 Fragment）的生命周期状态的变化。这些组件有助于您写出更有条理且往往更精简的代码，这样的代码更易于维护。 |
| [hilt][hilt] | 2.40.5 | 扩展了 Dagger Hilt 的功能，以实现 AndroidX 库中某些类的依赖项注入 |
| [navigation][navigation] | 2.4.0 | Navigation 是一个框架，用于在 Android 应用中的“目标”之间导航，该框架提供一致的 API，无论目标是作为 Fragment、Activity 还是其他组件实现 |
| [paging][paging] | 3.1.0 | 使用 Paging 库，您可以更轻松地在应用的 RecyclerView 中逐步妥善地加载数据。 
| [room][room] | 2.4.1 | Room 持久性库在 SQLite 的基础上提供了一个抽象层，让用户能够在充分利用 SQLite 的强大功能的同时，获享更强健的数据库访问机制。
| [work][work] | 2.5.0 | 使用 WorkManager API 可以轻松地调度那些必须可靠运行的可延期异步任务。通过这些 API，您可以创建任务并提交给 WorkManager，以便在满足工作约束条件时运行。

## 功能模块
[油价](doc/oil.md)

## 参考项目
[MVVM-Architecture][MVVM-Architecture]
[android-architecture-components][android-architecture-components]

## openApi
[今日国内油价][油价API]：每天50次免费调用,需注册并实名认证

## 参考文档
[调试workManager][调试workManager]
[使用 WorkManager 进行集成测试][使用 WorkManager 进行集成测试]
[WorkManager: 周期性任务][WorkManager: 周期性任务]
[WorkManager 最全攻略][WorkManager 最全攻略]

[startup]: https://developer.android.google.cn/jetpack/androidx/releases/startup
[lifecycle]: https://developer.android.google.cn/jetpack/androidx/releases/lifecycle
[hilt]: https://developer.android.google.cn/jetpack/androidx/releases/hilt
[navigation]: https://developer.android.google.cn/jetpack/androidx/releases/navigation
[paging]: https://developer.android.google.cn/jetpack/androidx/releases/paging?hl=zh_cn
[room]: https://developer.android.google.cn/jetpack/androidx/releases/room?hl=zh_cn
[work]: https://developer.android.google.cn/jetpack/androidx/releases/work?hl=zh_cn
[油价API]: https://www.juhe.cn/docs/api/id/540
[调试workManager]: https://developer.android.google.cn/topic/libraries/architecture/workmanager/how-to/debugging
[使用 WorkManager 进行集成测试]: https://developer.android.google.cn/topic/libraries/architecture/workmanager/how-to/integration-testing
[WorkManager: 周期性任务]: https://zhuanlan.zhihu.com/p/265051417?utm_source=wechat_session
[WorkManager 最全攻略]: https://www.jianshu.com/p/6a72bcd25956
[MVVM-Architecture]: https://github.com/qingmei2/MVVM-Architecture
[android-architecture-components]: https://github.com/googlesamples/android-architecture-components