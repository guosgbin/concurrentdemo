###demo01包

- Request：运营提出的需求都会封装成Request对象。
- Programmer：代表了每一个程序员接到需求后的处理逻辑。
- ProductManager：代表了产品经理，当运营有需求时，产品经理会将运营的需求封装成一个工单Request，然后开辟一个线程（程序员）去处理。

###demo02包

- Main：向Host发送字符显示请求的类。
- Host：针对请求创建线程的类。
- Helper：提供字符显示功能的被动类。

现象：调用request方法的线程会立即返回，而handler方法则交由其他线程来执行。