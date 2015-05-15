# tppd-mvp
A mvp implement

### 1、为什么要用 MVP？
1)、Model与View完全分离，它们通过接口进行交互，降低耦合度，便于维护和测试。

2)、可以更高效地使用Model，因为所有对Model的操作都在Presenter内部。

3)、我们可以将一个Presener用于多个视图，只需要在Presenter中为不同的View定义View Interface即可，具体的View实现自己的View Interface，即可使用Presenter中的Model操作等。

4)、同一界面数据重用（Activity 重启，进程重启，保证数据唯一性与可重用）