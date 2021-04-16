###autosave
模拟自动保存
- Data：表示可以修改并保存的数据的类
- SaverThread：定期保存数据内容的类，例如IDEA的自动保存功能
- ChangerThread： 主动保存数据内容的类，例如我们手动调用crtl+s
- Main：测试方法

现象是：每次打印的编号都不会有重复，这是因为守护条件是change

### ideaautosave
笔记文件的案例，自动保存，其实就和上面是一样的
- CodeFile：模拟代码文件，里面有change方法改变守护对象状态，有save方法做保存操作。
- CtrlsThread：模拟码农手动Ctrl+S保存文件，首先调用change方法代表修改代码，然后调用save方法保存。
- IdeaAutoSaveThread：模拟IDEA自动保存文件，每隔几秒钟调用依次save方法。
- Main：测试类。