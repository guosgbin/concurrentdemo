package com.peijun.balking.ideaautosave;

import com.peijun.balking.autosave.ChangerThread;
import com.peijun.balking.autosave.Data;
import com.peijun.balking.autosave.SaverThread;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 8:51
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        CodeFile codeFile = new CodeFile("" ,"codeFile.txt");
        new CtrlsThread("码农手动ctrl+s", codeFile).start();
        new IdeaAutoSaveThread("IDEA自动保存", codeFile).start();
    }
}
