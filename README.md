# myDxBall
复刻游戏DxBall


# 如何开始工作？
1.类Map是一个游戏关卡实例，通过实例化Map类，并对依次调用initGame()初始化游戏对象；方法initUI()进行显示游戏画面;方法run()运行游戏。

在方法initGame()中：
初始化游戏对象的布局。

在initUI()中：
设置游戏画面的布局、大小等。

在run()中：
定义游戏的主循环，每次循环对游戏对象进行更新，并重新绘制游戏画面。

2.通过创建新的类继承自Map，重写方法initGame()、initUI()完成新的游戏关卡设置。
